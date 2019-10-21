package com.zoo.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Jvar Grpc服务启动类
 */
public class JvarTransferServer {

    private static final Logger logger = LoggerFactory.getLogger(JvarTransferServer.class);

    private volatile boolean closed = false;
    private final Object closeLock = new Object();

    @Getter
    private int port;
    private static Server server;


    public JvarTransferServer(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        JvarTransferService jvarTransferService = new JvarTransferService();
//        NettyServerBuilder.forPort(port).addService(jvarTransferService);
        server = ServerBuilder.forPort(port).addService(jvarTransferService).build().start();
        logger.info("Server start success on port:" + port);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            // Use stderr here since the logger may have been reset by its JVM shutdown hook.
            logger.info("*** shutting down gRPC server since JVM is shutting down");
            JvarTransferServer.this.shutdown();
            logger.info("*** server shut down");
        }));
    }

    private void shutdown() {
        synchronized (closeLock) {
            if (closed || server == null) {
                return;
            }
            closed = true;
        }
        server.shutdown();
    }


    /**
     * Await termination on the main thread since the grpc library uses daemon threads.
     */
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            logger.info("blocking for termination...");
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        JvarTransferServer server = new JvarTransferServer(8893);
        server.start();
        server.blockUntilShutdown();
    }
}
