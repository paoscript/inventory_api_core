package com.dicapisar.inventory_api_core.repositories;

import com.dicapisar.inventory_api_core.models.Contact;
import com.dicapisar.inventory_api_core.models.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IContactRepository extends JpaRepository<Contact, Long> {
    @Query("select c from Contact c where c.active =:isActive")
    List<Contact> getListContact(@Param("isActive") Boolean isActive);

    @Query("select c from Contact c where c.active =:isActive and c.provider.id=:idProvider")
    List<Contact> getListContactByIdProvider(@Param("isActive") Boolean isActive, @Param("idProvider") Long idProvider);

}
