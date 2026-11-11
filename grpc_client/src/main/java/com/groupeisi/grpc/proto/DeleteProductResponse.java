package com.groupeisi.grpc.proto;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public final class DeleteProductResponse {
    private static final DeleteProductResponse DEFAULT_INSTANCE = new DeleteProductResponse(false, "");
    private final boolean success;
    private final String message;

    private DeleteProductResponse(boolean success, String message) {
        this.success = success;
        this.message = message != null ? message : "";
    }

    public boolean getSuccess() { return success; }
    public String getMessage() { return message; }

    public static DeleteProductResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public byte[] toByteArray() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            CodedOutputStream cos = CodedOutputStream.newInstance(baos);
            if (success) cos.writeBool(1, success);
            if (!message.isEmpty()) cos.writeString(2, message);
            cos.flush();
            return baos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static DeleteProductResponse parseFrom(InputStream input) throws IOException {
        CodedInputStream cis = CodedInputStream.newInstance(input);
        Builder builder = newBuilder();
        while (!cis.isAtEnd()) {
            int tag = cis.readTag();
            switch (tag) {
                case 0:
                    return builder.build();
                case 8:
                    builder.setSuccess(cis.readBool());
                    break;
                case 18:
                    builder.setMessage(cis.readStringRequireUtf8());
                    break;
                default:
                    cis.skipField(tag);
                    break;
            }
        }
        return builder.build();
    }

    public static final class Builder {
        private boolean success;
        private String message = "";

        private Builder() {}

        public Builder setSuccess(boolean success) { this.success = success; return this; }
        public Builder setMessage(String message) { this.message = message != null ? message : ""; return this; }

        public DeleteProductResponse build() {
            return new DeleteProductResponse(success, message);
        }
    }
}
