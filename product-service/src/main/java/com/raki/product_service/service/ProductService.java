package com.raki.product_service.service;

import com.raki.product_service.model.Product;
import com.raki.product_service.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepo repo;
    ProductService(ProductRepo repo){
        this.repo=repo;
    }
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = repo.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    public ResponseEntity<Product> getProductById(Integer id) {
        return repo.findById(id).map(product -> new ResponseEntity<>(product,HttpStatus.OK))
                .orElseGet(()-> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<String> createProduct(Product product) {
        repo.save(product);
        return new ResponseEntity<>("Success",HttpStatus.OK);
    }

    public ResponseEntity<String> deleteById(Integer id) {
       return repo.findById(id).map(product -> {
           repo.delete(product);
           return new ResponseEntity<>("Success",HttpStatus.OK);
       }).orElseGet(()-> new ResponseEntity<>("Product Not Found",HttpStatus.NOT_FOUND));
    }
}
