package com.dicapisar.inventory_api_core.repositories;

import com.dicapisar.inventory_api_core.models.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProviderRepository extends JpaRepository<Provider, Long> {
    @Query("select p from Provider p where p.active =:isActive")
    List<Provider> getListProvider(@Param("isActive") Boolean isActive);
}
