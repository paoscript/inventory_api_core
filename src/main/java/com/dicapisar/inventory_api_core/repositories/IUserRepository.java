package com.dicapisar.inventory_api_core.repositories;

import com.dicapisar.inventory_api_core.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    User findUserById(Long id);
}
