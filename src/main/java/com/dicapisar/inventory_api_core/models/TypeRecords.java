package com.dicapisar.inventory_api_core.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "types_records")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TypeRecords {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "typ_rec_id")
    private Long id;

    @Column(name = "typ_rec_english_name", nullable = false)
    private String englishName;
    
    @Column(name = "typ_rec_spanish_name", nullable = false)
    private String spanishName;

    @Column(name = "typ_rec_url", nullable = false)
    private String url;

    @Column(name = "typ_rec_active", nullable = false)
    private boolean active;
}
