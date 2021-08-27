package com.dicapisar.inventory_api_core.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "items")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ite_id")
    private Long id;

    @Column(name = "ite_name", nullable = false)
    private String name;

    @Column(name = "ite_count", nullable = false)
    private int count;

    @Column(name = "ite_average_cost", nullable = false)
    private double averageCost;

    @Column(name = "ite_price", nullable = false)
    private int price;

    @Column(name = "ite_date_perishable", nullable = false)
    private LocalDateTime datePerishable;

    @Column(name = "ite_barcode", nullable = false)
    private String barcode;

    @Column(name = "ite_qr_code", nullable = false)
    private String qrCode;

    @Column(name = "ite_active", nullable = false)
    private boolean active;

    @Column(name = "ite_created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "ite_updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ite_created_by_id")
    private User creator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ite_updated_by_id")
    private User updater;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ite_type_item_id")
    private TypeItem typeItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ite_brand_id")
    private Brand brand;
    
    
    
    
    
    
    
    
}
