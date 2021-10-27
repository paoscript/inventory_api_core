package com.dicapisar.inventory_api_core.repositories;

import com.dicapisar.inventory_api_core.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IItemRepository extends JpaRepository<Item, Long> {

}
