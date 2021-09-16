package com.dicapisar.inventory_api_core.repositories;

import com.dicapisar.inventory_api_core.models.TypeRecords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITypeRecordsRepository extends JpaRepository<TypeRecords, Long> {
    @Query("select t from TypeRecords t where t.active=true")
    List<TypeRecords> getListTypeRecords();
}
