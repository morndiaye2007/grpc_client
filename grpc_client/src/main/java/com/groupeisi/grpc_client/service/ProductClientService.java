package com.groupeisi.grpc_client.service;

import com.groupeisi.grpc.proto.CreateProductRequest;
import com.groupeisi.grpc.proto.DeleteProductResponse;
import com.groupeisi.grpc.proto.EmptyRequest;
import com.groupeisi.grpc.proto.ProductDto;
import com.groupeisi.grpc.proto.ProductIdRequest;
import com.groupeisi.grpc.proto.ProductListResponse;
import com.groupeisi.grpc.proto.ProductServiceGrpc;
import com.groupeisi.grpc.proto.UpdateProductRequest;
import com.groupeisi.grpc_client.dto.ProductModel;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductClientService {

    @GrpcClient("product-service")
    private ProductServiceGrpc.ProductServiceBlockingStub productStub;

    public ProductModel createProduct(ProductModel model) {
        CreateProductRequest request = CreateProductRequest.newBuilder()
                .setName(model.getName() != null ? model.getName() : "")
                .setDescription(model.getDescription() != null ? model.getDescription() : "")
                .setPrice(model.getPrice())
                .setQuantity(model.getQuantity())
                .build();
        ProductDto response = productStub.createProduct(request);
        return toModel(response);
    }

    public ProductModel getProductById(Long id) {
        ProductIdRequest request = ProductIdRequest.newBuilder()
                .setId(id)
                .build();
        ProductDto response = productStub.getProductById(request);
        return toModel(response);
    }

    public List<ProductModel> getAllProducts() {
        ProductListResponse response = productStub.getAllProducts(EmptyRequest.newBuilder().build());
        List<ProductModel> products = new ArrayList<>();
        for (ProductDto dto : response.getProductsList()) {
            products.add(toModel(dto));
        }
        return products;
    }

    public ProductModel updateProduct(Long id, ProductModel model) {
        UpdateProductRequest request = UpdateProductRequest.newBuilder()
                .setId(id)
                .setName(model.getName() != null ? model.getName() : "")
                .setDescription(model.getDescription() != null ? model.getDescription() : "")
                .setPrice(model.getPrice())
                .setQuantity(model.getQuantity())
                .build();
        ProductDto response = productStub.updateProduct(request);
        return toModel(response);
    }

    public Map<String, Object> deleteProduct(Long id) {
        ProductIdRequest request = ProductIdRequest.newBuilder()
                .setId(id)
                .build();
        DeleteProductResponse response = productStub.deleteProduct(request);
        Map<String, Object> result = new HashMap<>();
        result.put("success", response.getSuccess());
        result.put("message", response.getMessage());
        return result;
    }

    private ProductModel toModel(ProductDto dto) {
        return new ProductModel(
                dto.getId(),
                dto.getName(),
                dto.getDescription(),
                dto.getPrice(),
                dto.getQuantity()
        );
    }
}
