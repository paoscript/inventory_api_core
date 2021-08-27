package com.dicapisar.inventory_api_core.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "contacts")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "con_id")
    private Long id;

    @Column(name = "con_name", nullable = false)
    private String name;

    @Column(name = "con_phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "con_email", nullable = false)
    private String email;

    @Column(name = "con_addres", nullable = false)
    private String addres;

    @Column(name = "con_active", nullable = false)
    private boolean active;

    @Column(name = "con_created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "con_updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "con_created_by_id")
    private User creator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "con_updated_by_id")
    private User updater;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "con_provider_id")
    private Provider provider;

}
