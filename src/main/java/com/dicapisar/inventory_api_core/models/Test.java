package com.dicapisar.inventory_api_core.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "prueba_table")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prueba_id")
    private Long id;

    @Column(name = "prueba_name", nullable = false)
    private String name;
}
