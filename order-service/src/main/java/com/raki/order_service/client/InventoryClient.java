package com.raki.order_service.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "inventory-service",url="http://localhost:8083")
public interface InventoryClient {
    @GetMapping("/inventory/{id}")
    Integer getStock(@PathVariable Integer id);

    @PutMapping("/inventory/{id}/{quantity}")
    String updateStock(@PathVariable Integer id,@PathVariable Integer quantity);
}
