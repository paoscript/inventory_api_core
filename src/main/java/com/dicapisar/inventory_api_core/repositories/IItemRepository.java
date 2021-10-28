package com.dicapisar.inventory_api_core.repositories;

import com.dicapisar.inventory_api_core.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface IItemRepository extends JpaRepository<Item, Long> {

    @Query("select i from Item i where i.active =:isActive")
    List<Item> getListBrand(@Param("isActive") Boolean isActive);

    Item findItemByIdAndActive(Long id, Boolean active);

    @Query("select i from Item i where i.name =:name")
    List<Item> getItemByName(@Param("name") String name);

    @Transactional
    @Modifying
    @Query(value = "insert into items (ite_name, ite_count, ite_price, ite_average_cost, ite_barcode, ite_qr_code, ite_active, ite_created_at, ite_updated_at, ite_created_by_id, ite_updated_by_id, ite_type_item_id, ite_brand_id) values (:name, 0, :price, 0, :barCode, :qrCode, true, current_time(), current_time(), :idUser, :idUser, :idTypeItem, :idBrand)", nativeQuery = true)
    void insertItem(@Param("name") String name,@Param("price") int price,@Param("barCode") String barCode,@Param("qrCode") String qrCode,@Param("idTypeItem") Long idTypeItem,@Param("idBrand") Long idBrand, @Param("idUser") Long idUser);

}
