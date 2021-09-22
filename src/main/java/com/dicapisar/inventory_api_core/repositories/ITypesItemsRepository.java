package com.dicapisar.inventory_api_core.repositories;

import com.dicapisar.inventory_api_core.models.TypeItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ITypesItemsRepository extends JpaRepository<TypeItems, Long> {

    @Query("select t from TypeItems t where t.active =:isActive")
    List<TypeItems> getListTypesItems(@Param("isActive") Boolean isActive);

    @Query("select t from TypeItems t where t.name =:name")
    List<TypeItems> getItemTypeByName(@Param("name") String name);

    /**
     *
     Brand findBrandByIdAndActive(long id, Boolean active);
     */

    @Transactional
    @Modifying
    @Query(value = "insert into types_items (typ_name, typ_perishable, typ_active, typ_created_at, typ_updated_at, typ_created_by_id, typ_updated_by_id) values (:name, :isPerishable, true, current_timestamp, current_timestamp, :idUser, :idUser)", nativeQuery = true)
    void insertTypeItem(@Param("name") String name, @Param("idUser") Long idUser, @Param("isPerishable") boolean isPerishable);



}
