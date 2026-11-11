package com.groupeisi.grpc.proto;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public final class EmptyRequest {
    private static final EmptyRequest DEFAULT_INSTANCE = new EmptyRequest();

    private EmptyRequest() {}

    public static EmptyRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public byte[] toByteArray() {
        return new byte[0];
    }

    public static EmptyRequest parseFrom(InputStream input) throws IOException {
        CodedInputStream cis = CodedInputStream.newInstance(input);
        while (!cis.isAtEnd()) {
            int tag = cis.readTag();
            if (tag == 0) break;
            cis.skipField(tag);
        }
        return DEFAULT_INSTANCE;
    }

    public static final class Builder {
        private Builder() {}
        public EmptyRequest build() {
            return DEFAULT_INSTANCE;
        }
    }
}
