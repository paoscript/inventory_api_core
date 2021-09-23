package com.dicapisar.inventory_api_core.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "types_items")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TypeItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "typ_id")
    private Long id;

    @Column(name = "typ_name", nullable = false)
    private String name;
    
    @Column(name = "typ_perishable", nullable = false)
    private boolean perishable;

    @Column(name = "typ_active", nullable = false)
    private boolean active;

    @Column(name = "typ_created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "typ_updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "typ_created_by_id")
    private User creator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "typ_updated_by_id")
    private User updater;
}
