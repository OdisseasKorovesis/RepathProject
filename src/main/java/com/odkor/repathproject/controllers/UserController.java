package com.odkor.repathproject.controllers;

import com.odkor.repathproject.models.Company;
import com.odkor.repathproject.models.User;
import com.odkor.repathproject.repositories.CompanyRepository;
import com.odkor.repathproject.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Objects.isNull;

@RestController
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers() {

        logger.info("Attempting to retrieve users from database...");

        try {
            List<User> allUsers = (List<User>) userRepository.findAll();

            if(allUsers.isEmpty()) {
                logger.info("Attempt was successful, but list of users was empty.");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            logger.info("Attempt was successful, list of users was returned.");
            return new ResponseEntity<>(allUsers, HttpStatus.OK);
        } catch (Exception ex) {
            logger.info("Attempt was unsuccessful.");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/addUser", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> createUser(@RequestBody User user) {

        logger.info("Attempting to create new user with info" + user.toString());

        try {
            User userToBeCreated = new User();
            userToBeCreated.setName(user.getName());
            userToBeCreated.setEmail(user.getEmail());
            userToBeCreated.setCompanyId(user.getCompanyId());
            userRepository.save(userToBeCreated);

            logger.info("Created user successfully.");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            logger.info("Could not create new user due to exception.");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/deleteUser/{userId}")
    public ResponseEntity<String> deleteUserById(@PathVariable("userId") Long id) {

        logger.info("Attempting to delete user with id " + id);

        try {
            userRepository.deleteById(id);

            logger.info("User was deleted successfully.");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            logger.info("Could not delete user due to exception.");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getUsersByCompany/{companyId}")
    public ResponseEntity<List<User>> getUsersByCompany(@PathVariable("companyId") Long id) {
        logger.info("Attempting to retrieve users from database...");

        try {
            List<User> users = (List<User>) userRepository.findUsersByCompany(id);

            if(users.isEmpty()) {
                logger.info("Attempt was successful, but list of users was empty.");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            logger.info("Attempt was successful, list of users was returned.");
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception ex) {
            logger.info("Attempt was unsuccessful.");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/updateUser", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> updateUser(@RequestBody User user) {

        Long id = user.getId();
        logger.info("Attempting to update user with id " + user.getId());

        try {

            if(isNull(userRepository.findUserById(id))) {
                logger.info("Selected user does not exist, update cannot continue.");
                throw new Exception();
            }

            User updatedUser = new User();
            updatedUser.setId(user.getId());
            updatedUser.setName(user.getName());
            updatedUser.setEmail(user.getEmail());
            updatedUser.setCompanyId(user.getCompanyId());

            userRepository.save(user);

            logger.info("Updated user successfully.");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            logger.info("Could not create new user due to exception.");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
