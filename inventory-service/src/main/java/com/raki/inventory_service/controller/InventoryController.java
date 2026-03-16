package com.raki.inventory_service.controller;

import com.raki.inventory_service.model.Inventory;
import com.raki.inventory_service.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService service;
    InventoryController(InventoryService service){
        this.service=service;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Integer> getStock(@PathVariable Integer id){
        return service.getStock(id);
    }

    @PostMapping
    public ResponseEntity<String> createStock(@RequestBody Inventory inventory){
        return service.createStock(inventory);
    }

    @PutMapping("/{id}/{quantity}")
    public ResponseEntity<String> updateStock(@PathVariable Integer id,@PathVariable Integer  quantity){
        return service.updateStock(id,quantity);
    }
}
