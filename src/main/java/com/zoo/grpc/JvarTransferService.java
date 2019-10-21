package com.zoo.grpc;

import com.zoo.grpc.Element.BatchRequest;
import com.zoo.grpc.Element.BatchResponse;
import com.zoo.grpc.Element.BatchResponse.ResponseBody;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Jvar批量rpc查询服务端
 */
public class JvarTransferService extends JvarTransferGrpc.JvarTransferImplBase {

    private static final Logger logger = LoggerFactory.getLogger(JvarTransferService.class);

    /**
     * 查询业务实现方法
     * @param responseObserver 流式观察者
     */
/*    @Override
    public void getJvar(BatchRequest request, StreamObserver<BatchResponse> responseObserver) {
        logger.info("Server(Service) Received：{}", request);
        ResponseBody body = ResponseBody.newBuilder().addRmsks("rmsk").build();
        BatchResponse response = BatchResponse.newBuilder().addData(body).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }*/
    @Override
    public StreamObserver<BatchRequest> getJvar(StreamObserver<BatchResponse> responseObserver) {
        return new StreamResponseObserver(responseObserver);
    }

    private static class StreamResponseObserver implements StreamObserver<BatchRequest>{

        private StreamObserver<BatchResponse> responseObserver;

        public StreamResponseObserver(StreamObserver<BatchResponse> responseObserver) {
            this.responseObserver = responseObserver;
        }

        @Override
        public void onNext(BatchRequest request) {
            logger.info("received :" + request);
            ResponseBody body = ResponseBody.newBuilder().addRmsks("rmsk").build();
            BatchResponse response = BatchResponse.newBuilder().addData(body).build();
            responseObserver.onNext(response);//返回响应
        }

        @Override
        public void onError(Throwable t) {
            logger.warn("throw an error :", t);
        }

        @Override
        public void onCompleted() {
            logger.info("Server onCompleted");
//            ResponseBody body = ResponseBody.newBuilder().addRmsks("rmsk").build();
//            BatchResponse response = BatchResponse.newBuilder().addData(body).build();
//            responseObserver.onNext(response);//返回响应
            responseObserver.onCompleted();
        }
    }
}
