package com.odkor.repathproject.services;

import com.odkor.repathproject.models.User;

import java.util.List;

public interface UserService {

    void saveUser(User user);
    List<User> findAllUsers();
    User findUserById(Long Id);
    void deleteUserById(Long id);
    List<User> findUsersByCompany(Long companyId);

    //void updateUser(User user);

}
