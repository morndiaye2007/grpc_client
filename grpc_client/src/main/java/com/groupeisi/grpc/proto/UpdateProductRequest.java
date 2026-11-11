package com.groupeisi.grpc.proto;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public final class UpdateProductRequest {
    private static final UpdateProductRequest DEFAULT_INSTANCE = new UpdateProductRequest(0L, "", "", 0.0, 0);
    private final long id;
    private final String name;
    private final String description;
    private final double price;
    private final int quantity;

    private UpdateProductRequest(long id, String name, String description, double price, int quantity) {
        this.id = id;
        this.name = name != null ? name : "";
        this.description = description != null ? description : "";
        this.price = price;
        this.quantity = quantity;
    }

    public long getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }

    public static UpdateProductRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public byte[] toByteArray() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            CodedOutputStream cos = CodedOutputStream.newInstance(baos);
            if (id != 0L) cos.writeInt64(1, id);
            if (!name.isEmpty()) cos.writeString(2, name);
            if (!description.isEmpty()) cos.writeString(3, description);
            if (Double.doubleToRawLongBits(price) != 0L) cos.writeDouble(4, price);
            if (quantity != 0) cos.writeInt32(5, quantity);
            cos.flush();
            return baos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static UpdateProductRequest parseFrom(InputStream input) throws IOException {
        CodedInputStream cis = CodedInputStream.newInstance(input);
        Builder builder = newBuilder();
        while (!cis.isAtEnd()) {
            int tag = cis.readTag();
            switch (tag) {
                case 0:
                    return builder.build();
                case 8:
                    builder.setId(cis.readInt64());
                    break;
                case 18:
                    builder.setName(cis.readStringRequireUtf8());
                    break;
                case 26:
                    builder.setDescription(cis.readStringRequireUtf8());
                    break;
                case 33:
                    builder.setPrice(cis.readDouble());
                    break;
                case 40:
                    builder.setQuantity(cis.readInt32());
                    break;
                default:
                    cis.skipField(tag);
                    break;
            }
        }
        return builder.build();
    }

    public static final class Builder {
        private long id;
        private String name = "";
        private String description = "";
        private double price;
        private int quantity;

        private Builder() {}

        public Builder setId(long id) { this.id = id; return this; }
        public Builder setName(String name) { this.name = name != null ? name : ""; return this; }
        public Builder setDescription(String description) { this.description = description != null ? description : ""; return this; }
        public Builder setPrice(double price) { this.price = price; return this; }
        public Builder setQuantity(int quantity) { this.quantity = quantity; return this; }

        public UpdateProductRequest build() {
            return new UpdateProductRequest(id, name, description, price, quantity);
        }
    }
}
