package com.raki.product_service.controller;

import com.raki.product_service.model.Product;
import com.raki.product_service.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {

    private final ProductService service;
    ProductController(ProductService service){
        this.service=service;
    }
    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts(){
        return service.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id){
        return service.getProductById(id);
    }

    @PostMapping()
    public  ResponseEntity<String> createProduct(@RequestBody Product product){
        return service.createProduct(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable Integer id){
        return service.deleteById(id);
    }
}
