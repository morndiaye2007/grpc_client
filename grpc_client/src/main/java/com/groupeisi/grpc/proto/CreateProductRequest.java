package com.groupeisi.grpc.proto;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public final class CreateProductRequest {
    private static final CreateProductRequest DEFAULT_INSTANCE = new CreateProductRequest("", "", 0.0, 0);
    private final String name;
    private final String description;
    private final double price;
    private final int quantity;

    private CreateProductRequest(String name, String description, double price, int quantity) {
        this.name = name != null ? name : "";
        this.description = description != null ? description : "";
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }

    public static CreateProductRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public byte[] toByteArray() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            CodedOutputStream cos = CodedOutputStream.newInstance(baos);
            if (!name.isEmpty()) cos.writeString(1, name);
            if (!description.isEmpty()) cos.writeString(2, description);
            if (Double.doubleToRawLongBits(price) != 0L) cos.writeDouble(3, price);
            if (quantity != 0) cos.writeInt32(4, quantity);
            cos.flush();
            return baos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static CreateProductRequest parseFrom(InputStream input) throws IOException {
        CodedInputStream cis = CodedInputStream.newInstance(input);
        Builder builder = newBuilder();
        while (!cis.isAtEnd()) {
            int tag = cis.readTag();
            switch (tag) {
                case 0:
                    return builder.build();
                case 10:
                    builder.setName(cis.readStringRequireUtf8());
                    break;
                case 18:
                    builder.setDescription(cis.readStringRequireUtf8());
                    break;
                case 25:
                    builder.setPrice(cis.readDouble());
                    break;
                case 32:
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
        private String name = "";
        private String description = "";
        private double price;
        private int quantity;

        private Builder() {}

        public Builder setName(String name) { this.name = name != null ? name : ""; return this; }
        public Builder setDescription(String description) { this.description = description != null ? description : ""; return this; }
        public Builder setPrice(double price) { this.price = price; return this; }
        public Builder setQuantity(int quantity) { this.quantity = quantity; return this; }

        public CreateProductRequest build() {
            return new CreateProductRequest(name, description, price, quantity);
        }
    }
}
