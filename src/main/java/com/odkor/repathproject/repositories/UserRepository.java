package com.odkor.repathproject.repositories;

import com.odkor.repathproject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM users WHERE company_id = ?1", nativeQuery = true)
    List<User> findUsersByCompany(Long companyId);

    User findUserById(Long id);
}
