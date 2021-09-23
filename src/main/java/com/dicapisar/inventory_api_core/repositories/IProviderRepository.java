package com.dicapisar.inventory_api_core.repositories;

import com.dicapisar.inventory_api_core.models.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface IProviderRepository extends JpaRepository<Provider, Long> {
    @Query("select p from Provider p where p.active =:isActive")
    List<Provider> getListProvider(@Param("isActive") Boolean isActive);

    @Query("select p from Provider p where p.name =:name")
    List<Provider> getProviderByName(@Param("name") String name);

    @Transactional
    @Modifying
    @Query(value = "insert into providers (pro_name, pro_document_number, pro_phone_number, pro_email, pro_address, pro_active, pro_created_at, pro_updated_at, pro_created_by_id, pro_updated_by_id) values (:name, :documentNumber, :phoneNumber, :email, :address, true, current_timestamp, current_timestamp, :idUser, :idUser)", nativeQuery = true)
    void insertProvider(@Param("name") String name, @Param("documentNumber") String documentNumber, @Param("phoneNumber") String phoneNumber, @Param("email") String email, @Param("address") String address, @Param("idUser") Long idUser);

    Provider findProviderByIdAndActive(Long id, Boolean active);
}
