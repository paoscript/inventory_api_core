package com.dicapisar.inventory_api_core.repositories;

import com.dicapisar.inventory_api_core.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface IContactRepository extends JpaRepository<Contact, Long> {
    @Query("select c from Contact c where c.active =:isActive")
    List<Contact> getListContact(@Param("isActive") Boolean isActive);

    @Query("select c from Contact c where c.active =:isActive and c.provider.id=:idProvider")
    List<Contact> getListContactByIdProvider(@Param("isActive") Boolean isActive, @Param("idProvider") Long idProvider);

    @Query("select c from Contact c where c.name =:name and c.provider.id=:idProvider")
    List<Contact> getContactByNameAndIdProvider(@Param("name") String name, @Param("idProvider") Long idProvider);

    @Transactional
    @Modifying
    @Query(value = "insert into contacts (con_name, con_phone_number, con_email, con_active, con_created_at, con_updated_at, con_created_by_id, con_updated_by_id, con_provider_id) values (:name, :phoneNumber, :email, true, current_timestamp, current_timestamp, :idUser, :idUser, :idProvider)", nativeQuery = true)
    void insertContact(@Param("name") String name, @Param("phoneNumber") String phoneNumber, @Param("email") String email, @Param("idUser") Long idUser, @Param("idProvider") Long idProvider);

    Contact findContactByIdAndActive(long id, Boolean active);
}
