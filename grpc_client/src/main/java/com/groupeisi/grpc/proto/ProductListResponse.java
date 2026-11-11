package com.groupeisi.grpc.proto;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ProductListResponse {
    private static final ProductListResponse DEFAULT_INSTANCE = new ProductListResponse(Collections.emptyList());
    private final List<ProductDto> products;

    private ProductListResponse(List<ProductDto> products) {
        this.products = Collections.unmodifiableList(new ArrayList<>(products));
    }

    public List<ProductDto> getProductsList() {
        return products;
    }

    public int getProductsCount() {
        return products.size();
    }

    public ProductDto getProducts(int index) {
        return products.get(index);
    }

    public static ProductListResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public byte[] toByteArray() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            CodedOutputStream cos = CodedOutputStream.newInstance(baos);
            for (ProductDto product : products) {
                byte[] productBytes = product.toByteArray();
                cos.writeByteArray(1, productBytes);
            }
            cos.flush();
            return baos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ProductListResponse parseFrom(InputStream input) throws IOException {
        CodedInputStream cis = CodedInputStream.newInstance(input);
        Builder builder = newBuilder();
        while (!cis.isAtEnd()) {
            int tag = cis.readTag();
            switch (tag) {
                case 0:
                    return builder.build();
                case 10:
                    byte[] bytes = cis.readByteArray();
                    builder.addProducts(ProductDto.parseFrom(bytes));
                    break;
                default:
                    cis.skipField(tag);
                    break;
            }
        }
        return builder.build();
    }

    public static final class Builder {
        private final List<ProductDto> products = new ArrayList<>();

        private Builder() {}

        public Builder addProducts(ProductDto product) {
            if (product != null) {
                this.products.add(product);
            }
            return this;
        }

        public Builder addAllProducts(Iterable<? extends ProductDto> values) {
            if (values != null) {
                for (ProductDto p : values) {
                    this.products.add(p);
                }
            }
            return this;
        }

        public ProductListResponse build() {
            return new ProductListResponse(products);
        }
    }
}
