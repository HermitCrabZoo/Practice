package com.zoo.grpc;

import com.google.common.util.concurrent.SettableFuture;
import com.zoo.grpc.Element.BatchRequest;
import com.zoo.grpc.Element.BatchResponse;
import io.grpc.ManagedChannel;
import io.grpc.netty.shaded.io.grpc.netty.GrpcSslContexts;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;
import io.grpc.netty.shaded.io.netty.handler.ssl.SslContext;
import io.grpc.netty.shaded.io.netty.handler.ssl.SslContextBuilder;
import io.grpc.stub.ClientCallStreamObserver;
import io.grpc.stub.ClientResponseObserver;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLException;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Consumer;

public class JvarTransferClient {

    private static final Logger logger = LoggerFactory.getLogger(JvarTransferClient.class);

    private volatile boolean closed = false;
    private final Object closeLock = new Object();

    @Getter
    private String host;
    @Getter
    private int port;

    private final ManagedChannel channel;
    private final JvarTransferGrpc.JvarTransferStub stub;

    private static SslContext buildSslContext(String trustCertCollectionFilePath,
                                              String clientCertChainFilePath,
                                              String clientPrivateKeyFilePath) throws SSLException {
        SslContextBuilder builder = GrpcSslContexts.forClient();
        if (trustCertCollectionFilePath != null) {
            builder.trustManager(new File(trustCertCollectionFilePath));
        }
        if (clientCertChainFilePath != null && clientPrivateKeyFilePath != null) {
            builder.keyManager(new File(clientCertChainFilePath), new File(clientPrivateKeyFilePath));
        }
        return builder.build();
    }

    public JvarTransferClient(String host, int port) {
        this.host = host;
        this.port = port;
        this.channel = NettyChannelBuilder.forAddress(host, port).usePlaintext().build();
//        this.channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
        this.stub = JvarTransferGrpc.newStub(channel);
        logger.info("Client connected to Server:{} on port:{}", host, port);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            // Use stderr here since the logger may have been reset by its JVM shutdown hook.
            logger.info("*** shutting down gRPC client since JVM is shutting down");
            try {
                JvarTransferClient.this.shutdown();
                logger.info("*** client shut down");
            } catch (InterruptedException e) {
                logger.error("shut down grpc error:", e);
            }
        }));
    }

    public void shutdown() throws InterruptedException {
        synchronized (closeLock) {
            if (closed) {
                return;
            }
            closed = true;
        }
        channel.shutdown().awaitTermination(10, TimeUnit.SECONDS);
    }

    public ClientResponseStreamObserver getJvars(Iterator<BatchRequest> requests, Consumer<BatchResponse> consumer) {
        ClientResponseStreamObserver observer = new ClientResponseStreamObserver(requests, SettableFuture.create(), consumer);
        stub.getJvar(observer);
        return observer;
    }


    public static class ClientResponseStreamObserver implements ClientResponseObserver<BatchRequest, BatchResponse> {
        private ClientCallStreamObserver<BatchRequest> requestStream;

        private Iterator<BatchRequest> batches;
        @Getter
        private SettableFuture<Void> future;

        private Consumer<BatchResponse> consumer;

        public ClientResponseStreamObserver(Iterator<BatchRequest> batches, SettableFuture<Void> future, Consumer<BatchResponse> consumer) {
            this.batches = batches;
            this.future = future;
            this.consumer = consumer;
        }

        @Override
        public void beforeStart(ClientCallStreamObserver<BatchRequest> clientCallStreamObserver) {
            this.requestStream = clientCallStreamObserver;
            requestStream.disableAutoInboundFlowControl();
            // An iterator is used so we can pause and resume iteration of the request data.
            requestStream.setOnReadyHandler(() -> {
                // Start generating values from where we left off on a non-gRPC thread.
                while (requestStream.isReady()) {
                    if (batches.hasNext()) {
                        // Send more messages if there are more messages to send.
                        BatchRequest request = batches.next();
                        requestStream.onNext(request);
                    } else {
                        // Signal completion if there is nothing left to send.
                        requestStream.onCompleted();
                    }
                }
            });
        }


        @Override
        public void onNext(BatchResponse response) {
//            logger.info("Client get back：{}", response);
            logger.info("Client onNext");
            requestStream.request(1);
            consumer.accept(response);
        }

        @Override
        public void onError(Throwable throwable) {
            throwable.printStackTrace();
            future.setException(throwable);
        }

        @Override
        public void onCompleted() {
            logger.info("Client onCompleted");
            future.set(null);
        }

        public void waitCompleted() throws IOException {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new IOException(e);
            }
        }

        public void waitCompleted(long timeout, TimeUnit unit) throws IOException {
            try {
                future.get(timeout, unit);
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                throw new IOException(e);
            }
        }

        public boolean isDone(){
            return future.isDone();
        }
    }


    public static void main(String[] args) throws InterruptedException, IOException {
//        JvarTransferClient client = new JvarTransferClient("192.168.1.77", 7210);
/*        JvarTransferClient client = new JvarTransferClient("localhost", 8893);
        try {
            BatchRequest.RequestBody body = BatchRequest.RequestBody.newBuilder().setChr("1").build();
            BatchRequest request = BatchRequest.newBuilder().addData(body).build();
            BatchRequest.RequestBody body1 = BatchRequest.RequestBody.newBuilder().setChr("2").build();
            BatchRequest request1 = BatchRequest.newBuilder().addData(body1).build();
            ClientResponseStreamObserver observer = client.getJvars(List.of(request, request1).iterator(), response -> logger.info("Consumer get back：{}", response));
            observer.waitCompleted();
            logger.info("isDone:{}", observer.isDone());
            logger.info("getted");
            client.channel.awaitTermination(10, TimeUnit.SECONDS);
        } finally {
            client.shutdown();
        }*/
    }
}
