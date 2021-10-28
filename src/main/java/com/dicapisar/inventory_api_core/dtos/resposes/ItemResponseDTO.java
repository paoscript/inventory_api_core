package com.dicapisar.inventory_api_core.dtos.resposes;

import com.dicapisar.inventory_api_core.models.Brand;
import com.dicapisar.inventory_api_core.models.TypeItems;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemResponseDTO {
    private Long id;
    private String name;
    private int count;
    private double averageCost;
    private int price;
    private String barcode;
    private String qrCode;
    private TypesItemsResponseDTO typeItem;
    private BrandResponseDTO brand;
}
