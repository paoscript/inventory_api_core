package com.dicapisar.inventory_api_core.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "brands")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bra_id")
    private Long id;

    @Column(name = "bra_name", nullable = false)
    private String name;

    @Column(name = "bra_active", nullable = false)
    private boolean active;

    @Column(name = "bra_created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "bra_updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bra_created_by_id")
    private User creator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bra_updated_by_id")
    private User updater;
}
