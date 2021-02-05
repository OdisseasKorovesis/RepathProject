package com.odkor.repathproject.repositories;

import com.odkor.repathproject.models.Company;
import com.odkor.repathproject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM users WHERE company_id = ?1", nativeQuery = true)
    List<User> findUsersByCompany(Long companyId);

    User findUserById(Long id);

//    @Transactional
//    @Query(value = "UPDATE users SET name = ?1, email = ?2, company_id = ?3 WHERE id = ?4", nativeQuery = true)
//    List<User> updateUser(String name, String email, Long companyId, Long id);
}
