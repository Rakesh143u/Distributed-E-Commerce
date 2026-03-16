package com.raki.inventory_service.service;

import com.raki.inventory_service.model.Inventory;
import com.raki.inventory_service.repo.InventoryRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {
    private final InventoryRepo repo;
    InventoryService(InventoryRepo repo){
        this.repo=repo;
    }
    public ResponseEntity<Integer> getStock(Integer id) {
     Inventory inventory = repo.findByProductId(id);
     if(inventory==null){
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
     }
     return new ResponseEntity<>(inventory.getQuantity(), HttpStatus.OK);
    }

    public ResponseEntity<String> createStock(Inventory inventory) {
       if(repo.findByProductId(inventory.getProductId())!=null){
           return new ResponseEntity<>("Product ALready exists",HttpStatus.OK);
       }
       repo.save(inventory);
       return new ResponseEntity<>("New Stocks added",HttpStatus.OK);
    }

    public ResponseEntity<String> updateStock(Integer id, Integer quantity) {
        Inventory inventory = repo.findByProductId(id);
        if(inventory==null){
            return new ResponseEntity<>("Product Not Found",HttpStatus.NOT_FOUND);
        }
        inventory.setQuantity(inventory.getQuantity()+quantity);
        repo.save(inventory);
        return new ResponseEntity<>("New Stock Added",HttpStatus.OK);
    }
}
