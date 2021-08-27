package com.dicapisar.inventory_api_core.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "use_id")
    private Long id;

    @Column(name = "use_name", nullable = false)
    private String name;

    @Column(name = "use_password", nullable = false)
    private String password;

    @Column(name = "use_active", nullable = false)
    private boolean active;

    @Column(name = "use_created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "use_updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "use_created_by_id")
    private User creator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "use_updated_by_id")
    private User updater;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "use_rol")
    private Rol rol;

}
