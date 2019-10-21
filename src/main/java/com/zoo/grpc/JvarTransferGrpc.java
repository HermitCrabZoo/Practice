package com.zoo.grpc;

import io.grpc.stub.ClientCalls;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 * <pre>
 * data transfer service
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.24.0)",
    comments = "Source: Element.proto")
public final class JvarTransferGrpc {

  private JvarTransferGrpc() {}

  public static final String SERVICE_NAME = "com.zoo.grpc.JvarTransfer";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.zoo.grpc.Element.BatchRequest,
      com.zoo.grpc.Element.BatchResponse> getGetJvarMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getJvar",
      requestType = com.zoo.grpc.Element.BatchRequest.class,
      responseType = com.zoo.grpc.Element.BatchResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<com.zoo.grpc.Element.BatchRequest,
      com.zoo.grpc.Element.BatchResponse> getGetJvarMethod() {
    io.grpc.MethodDescriptor<com.zoo.grpc.Element.BatchRequest, com.zoo.grpc.Element.BatchResponse> getGetJvarMethod;
    if ((getGetJvarMethod = JvarTransferGrpc.getGetJvarMethod) == null) {
      synchronized (JvarTransferGrpc.class) {
        if ((getGetJvarMethod = JvarTransferGrpc.getGetJvarMethod) == null) {
          JvarTransferGrpc.getGetJvarMethod = getGetJvarMethod =
              io.grpc.MethodDescriptor.<com.zoo.grpc.Element.BatchRequest, com.zoo.grpc.Element.BatchResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getJvar"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.zoo.grpc.Element.BatchRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.zoo.grpc.Element.BatchResponse.getDefaultInstance()))
              .setSchemaDescriptor(new JvarTransferMethodDescriptorSupplier("getJvar"))
              .build();
        }
      }
    }
    return getGetJvarMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static JvarTransferStub newStub(io.grpc.Channel channel) {
    return new JvarTransferStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static JvarTransferBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new JvarTransferBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static JvarTransferFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new JvarTransferFutureStub(channel);
  }

  /**
   * <pre>
   * data transfer service
   * </pre>
   */
  public static abstract class JvarTransferImplBase implements io.grpc.BindableService {

    /**
     */
    public io.grpc.stub.StreamObserver<com.zoo.grpc.Element.BatchRequest> getJvar(
        io.grpc.stub.StreamObserver<com.zoo.grpc.Element.BatchResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getGetJvarMethod(), responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetJvarMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                com.zoo.grpc.Element.BatchRequest,
                com.zoo.grpc.Element.BatchResponse>(
                  this, METHODID_GET_JVAR)))
          .build();
    }
  }

  /**
   * <pre>
   * data transfer service
   * </pre>
   */
  public static final class JvarTransferStub extends io.grpc.stub.AbstractStub<JvarTransferStub> {
    private JvarTransferStub(io.grpc.Channel channel) {
      super(channel);
    }

    private JvarTransferStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected JvarTransferStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new JvarTransferStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.zoo.grpc.Element.BatchRequest> getJvar(
        io.grpc.stub.StreamObserver<com.zoo.grpc.Element.BatchResponse> responseObserver) {
      return ClientCalls.asyncBidiStreamingCall(
          getChannel().newCall(getGetJvarMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   * <pre>
   * data transfer service
   * </pre>
   */
  public static final class JvarTransferBlockingStub extends io.grpc.stub.AbstractStub<JvarTransferBlockingStub> {
    private JvarTransferBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private JvarTransferBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected JvarTransferBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new JvarTransferBlockingStub(channel, callOptions);
    }
  }

  /**
   * <pre>
   * data transfer service
   * </pre>
   */
  public static final class JvarTransferFutureStub extends io.grpc.stub.AbstractStub<JvarTransferFutureStub> {
    private JvarTransferFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private JvarTransferFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected JvarTransferFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new JvarTransferFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_GET_JVAR = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final JvarTransferImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(JvarTransferImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }

    @Override
    @SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_JVAR:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.getJvar(
              (io.grpc.stub.StreamObserver<com.zoo.grpc.Element.BatchResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class JvarTransferBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    JvarTransferBaseDescriptorSupplier() {}

    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.zoo.grpc.Element.getDescriptor();
    }

    @Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("JvarTransfer");
    }
  }

  private static final class JvarTransferFileDescriptorSupplier
      extends JvarTransferBaseDescriptorSupplier {
    JvarTransferFileDescriptorSupplier() {}
  }

  private static final class JvarTransferMethodDescriptorSupplier
      extends JvarTransferBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    JvarTransferMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (JvarTransferGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new JvarTransferFileDescriptorSupplier())
              .addMethod(getGetJvarMethod())
              .build();
        }
      }
    }
    return result;
  }
}
