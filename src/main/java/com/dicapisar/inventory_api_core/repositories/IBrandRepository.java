package com.dicapisar.inventory_api_core.repositories;

import com.dicapisar.inventory_api_core.models.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface IBrandRepository extends JpaRepository<Brand, Long> {

    @Query("select b from Brand b where b.active =:isActive")
    List<Brand> getListBrand(@Param("isActive") Boolean isActive);

    @Query("select b from Brand b where b.name =:name")
    List<Brand> getBrandsByName(@Param("name") String name);

    @Transactional
    @Modifying
    @Query(value = "insert into brands (bra_name, bra_active, bra_created_at, bra_updated_at, bra_created_by_id, bra_updated_by_id) values (:name, true, current_timestamp, current_timestamp, :idUser, :idUser)", nativeQuery = true)
    void insertBrand(@Param("name") String name, @Param("idUser") Long idUser);

    Brand findBrandByIdAndActive(long id, Boolean active);
}
