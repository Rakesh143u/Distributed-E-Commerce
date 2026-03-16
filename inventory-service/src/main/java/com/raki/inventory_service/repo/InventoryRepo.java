package com.raki.inventory_service.repo;

import com.raki.inventory_service.model.Inventory;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepo extends JpaRepository<Inventory,Integer> {

    Inventory findByProductId(Integer id);
}
