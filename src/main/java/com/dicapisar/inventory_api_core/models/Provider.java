package com.dicapisar.inventory_api_core.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "providers")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pro_id")
    private Long id;

    @Column(name = "pro_name", nullable = false)
    private String name;

    @Column(name = "pro_document_number", nullable = false)
    private String documentNumber;

    @Column(name = "pro_phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "pro_email", nullable = false)
    private String email;

    @Column(name = "pro_addres", nullable = false)
    private String addres;

    @Column(name = "pro_active", nullable = false)
    private boolean active;

    @Column(name = "pro_created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "pro_updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pro_created_by_id")
    private User creator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pro_updated_by_id")
    private User updater;
}
