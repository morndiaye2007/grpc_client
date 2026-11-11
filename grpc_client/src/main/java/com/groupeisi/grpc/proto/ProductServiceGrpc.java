package com.groupeisi.grpc.proto;

import io.grpc.BindableService;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.MethodDescriptor;
import io.grpc.ServerServiceDefinition;
import io.grpc.stub.AbstractAsyncStub;
import io.grpc.stub.AbstractBlockingStub;
import io.grpc.stub.AbstractFutureStub;
import io.grpc.stub.AbstractStub;
import io.grpc.stub.ClientCalls;
import io.grpc.stub.ServerCalls;
import io.grpc.stub.StreamObserver;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.Function;

public final class ProductServiceGrpc {

    private ProductServiceGrpc() {}

    public static final String SERVICE_NAME = "product.ProductService";

    private static <T> MethodDescriptor.Marshaller<T> marshaller(
            Function<T, byte[]> serializer,
            IOFunction<InputStream, T> parser) {
        return new MethodDescriptor.Marshaller<T>() {
            @Override
            public InputStream stream(T value) {
                return new ByteArrayInputStream(serializer.apply(value));
            }

            @Override
            public T parse(InputStream stream) {
                try {
                    return parser.apply(stream);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }

    @FunctionalInterface
    private interface IOFunction<I, O> {
        O apply(I input) throws IOException;
    }

    public static final MethodDescriptor<CreateProductRequest, ProductDto> getCreateProductMethod() {
        return MethodDescriptor.<CreateProductRequest, ProductDto>newBuilder()
                .setType(MethodDescriptor.MethodType.UNARY)
                .setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "CreateProduct"))
                .setRequestMarshaller(marshaller(CreateProductRequest::toByteArray, CreateProductRequest::parseFrom))
                .setResponseMarshaller(marshaller(ProductDto::toByteArray, ProductDto::parseFrom))
                .build();
    }

    public static final MethodDescriptor<ProductIdRequest, ProductDto> getGetProductByIdMethod() {
        return MethodDescriptor.<ProductIdRequest, ProductDto>newBuilder()
                .setType(MethodDescriptor.MethodType.UNARY)
                .setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetProductById"))
                .setRequestMarshaller(marshaller(ProductIdRequest::toByteArray, ProductIdRequest::parseFrom))
                .setResponseMarshaller(marshaller(ProductDto::toByteArray, ProductDto::parseFrom))
                .build();
    }

    public static final MethodDescriptor<EmptyRequest, ProductListResponse> getGetAllProductsMethod() {
        return MethodDescriptor.<EmptyRequest, ProductListResponse>newBuilder()
                .setType(MethodDescriptor.MethodType.UNARY)
                .setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetAllProducts"))
                .setRequestMarshaller(marshaller(EmptyRequest::toByteArray, EmptyRequest::parseFrom))
                .setResponseMarshaller(marshaller(ProductListResponse::toByteArray, ProductListResponse::parseFrom))
                .build();
    }

    public static final MethodDescriptor<UpdateProductRequest, ProductDto> getUpdateProductMethod() {
        return MethodDescriptor.<UpdateProductRequest, ProductDto>newBuilder()
                .setType(MethodDescriptor.MethodType.UNARY)
                .setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "UpdateProduct"))
                .setRequestMarshaller(marshaller(UpdateProductRequest::toByteArray, UpdateProductRequest::parseFrom))
                .setResponseMarshaller(marshaller(ProductDto::toByteArray, ProductDto::parseFrom))
                .build();
    }

    public static final MethodDescriptor<ProductIdRequest, DeleteProductResponse> getDeleteProductMethod() {
        return MethodDescriptor.<ProductIdRequest, DeleteProductResponse>newBuilder()
                .setType(MethodDescriptor.MethodType.UNARY)
                .setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "DeleteProduct"))
                .setRequestMarshaller(marshaller(ProductIdRequest::toByteArray, ProductIdRequest::parseFrom))
                .setResponseMarshaller(marshaller(DeleteProductResponse::toByteArray, DeleteProductResponse::parseFrom))
                .build();
    }

    public static ProductServiceStub newStub(Channel channel) {
        return ProductServiceStub.newStub(ProductServiceStub::new, channel);
    }

    public static ProductServiceBlockingStub newBlockingStub(Channel channel) {
        return ProductServiceBlockingStub.newStub(ProductServiceBlockingStub::new, channel);
    }

    public static ProductServiceFutureStub newFutureStub(Channel channel) {
        return ProductServiceFutureStub.newStub(ProductServiceFutureStub::new, channel);
    }

    public static abstract class ProductServiceImplBase implements BindableService {

        public void createProduct(CreateProductRequest request, StreamObserver<ProductDto> responseObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(getCreateProductMethod(), responseObserver);
        }

        public void getProductById(ProductIdRequest request, StreamObserver<ProductDto> responseObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(getGetProductByIdMethod(), responseObserver);
        }

        public void getAllProducts(EmptyRequest request, StreamObserver<ProductListResponse> responseObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(getGetAllProductsMethod(), responseObserver);
        }

        public void updateProduct(UpdateProductRequest request, StreamObserver<ProductDto> responseObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(getUpdateProductMethod(), responseObserver);
        }

        public void deleteProduct(ProductIdRequest request, StreamObserver<DeleteProductResponse> responseObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(getDeleteProductMethod(), responseObserver);
        }

        @Override
        public final ServerServiceDefinition bindService() {
            return ServerServiceDefinition.builder(SERVICE_NAME)
                    .addMethod(
                            getCreateProductMethod(),
                            ServerCalls.asyncUnaryCall(this::createProduct))
                    .addMethod(
                            getGetProductByIdMethod(),
                            ServerCalls.asyncUnaryCall(this::getProductById))
                    .addMethod(
                            getGetAllProductsMethod(),
                            ServerCalls.asyncUnaryCall(this::getAllProducts))
                    .addMethod(
                            getUpdateProductMethod(),
                            ServerCalls.asyncUnaryCall(this::updateProduct))
                    .addMethod(
                            getDeleteProductMethod(),
                            ServerCalls.asyncUnaryCall(this::deleteProduct))
                    .build();
        }
    }

    public static final class ProductServiceStub extends AbstractAsyncStub<ProductServiceStub> {
        private ProductServiceStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        @Override
        protected ProductServiceStub build(Channel channel, CallOptions callOptions) {
            return new ProductServiceStub(channel, callOptions);
        }
    }

    public static final class ProductServiceBlockingStub extends AbstractBlockingStub<ProductServiceBlockingStub> {
        private ProductServiceBlockingStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        @Override
        protected ProductServiceBlockingStub build(Channel channel, CallOptions callOptions) {
            return new ProductServiceBlockingStub(channel, callOptions);
        }

        public ProductDto createProduct(CreateProductRequest request) {
            return ClientCalls.blockingUnaryCall(getChannel(), getCreateProductMethod(), getCallOptions(), request);
        }

        public ProductDto getProductById(ProductIdRequest request) {
            return ClientCalls.blockingUnaryCall(getChannel(), getGetProductByIdMethod(), getCallOptions(), request);
        }

        public ProductListResponse getAllProducts(EmptyRequest request) {
            return ClientCalls.blockingUnaryCall(getChannel(), getGetAllProductsMethod(), getCallOptions(), request);
        }

        public ProductDto updateProduct(UpdateProductRequest request) {
            return ClientCalls.blockingUnaryCall(getChannel(), getUpdateProductMethod(), getCallOptions(), request);
        }

        public DeleteProductResponse deleteProduct(ProductIdRequest request) {
            return ClientCalls.blockingUnaryCall(getChannel(), getDeleteProductMethod(), getCallOptions(), request);
        }
    }

    public static final class ProductServiceFutureStub extends AbstractFutureStub<ProductServiceFutureStub> {
        private ProductServiceFutureStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        @Override
        protected ProductServiceFutureStub build(Channel channel, CallOptions callOptions) {
            return new ProductServiceFutureStub(channel, callOptions);
        }
    }
}
