package com.groupeisi.grpc.proto;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public final class ProductIdRequest {
    private static final ProductIdRequest DEFAULT_INSTANCE = new ProductIdRequest(0L);
    private final long id;

    private ProductIdRequest(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public static ProductIdRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public byte[] toByteArray() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            CodedOutputStream cos = CodedOutputStream.newInstance(baos);
            if (id != 0L) {
                cos.writeInt64(1, id);
            }
            cos.flush();
            return baos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ProductIdRequest parseFrom(InputStream input) throws IOException {
        CodedInputStream cis = CodedInputStream.newInstance(input);
        Builder builder = newBuilder();
        while (!cis.isAtEnd()) {
            int tag = cis.readTag();
            if (tag == 0) break;
            if (tag == 8) {
                builder.setId(cis.readInt64());
            } else {
                cis.skipField(tag);
            }
        }
        return builder.build();
    }

    public static final class Builder {
        private long id;

        private Builder() {}

        public Builder setId(long id) {
            this.id = id;
            return this;
        }

        public ProductIdRequest build() {
            return new ProductIdRequest(id);
        }
    }
}
