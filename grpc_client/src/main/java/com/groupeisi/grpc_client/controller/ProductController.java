package com.groupeisi.grpc_client.controller;

import com.groupeisi.grpc_client.dto.ProductModel;
import com.groupeisi.grpc_client.service.ProductClientService;
import io.grpc.StatusRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductClientService productClientService;

    public ProductController(ProductClientService productClientService) {
        this.productClientService = productClientService;
    }

    @GetMapping
    public ResponseEntity<List<ProductModel>> getAllProducts() {
        return ResponseEntity.ok(productClientService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductModel> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productClientService.getProductById(id));
    }

    @PostMapping
    public ResponseEntity<ProductModel> createProduct(@RequestBody ProductModel product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productClientService.createProduct(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductModel> updateProduct(@PathVariable Long id, @RequestBody ProductModel product) {
        return ResponseEntity.ok(productClientService.updateProduct(id, product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productClientService.deleteProduct(id));
    }

    @ExceptionHandler(StatusRuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleGrpcException(StatusRuntimeException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("code", ex.getStatus().getCode().name());
        error.put("message", ex.getStatus().getDescription());
        HttpStatus status = switch (ex.getStatus().getCode()) {
            case NOT_FOUND -> HttpStatus.NOT_FOUND;
            case INVALID_ARGUMENT -> HttpStatus.BAD_REQUEST;
            default -> HttpStatus.INTERNAL_SERVER_ERROR;
        };
        return ResponseEntity.status(status).body(error);
    }
}
