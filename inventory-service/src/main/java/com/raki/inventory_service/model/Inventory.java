package com.raki.inventory_service.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="inventory")
@Data
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer productId;
    private Integer quantity;
}
