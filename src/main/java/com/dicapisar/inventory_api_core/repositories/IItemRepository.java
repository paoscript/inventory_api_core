package com.dicapisar.inventory_api_core.repositories;

import com.dicapisar.inventory_api_core.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IItemRepository extends JpaRepository<Item, Long> {

    @Query("select i from Item i where i.active =:isActive")
    List<Item> getListBrand(@Param("isActive") Boolean isActive);

    Item findItemByIdAndActive(Long id, Boolean active);
}
