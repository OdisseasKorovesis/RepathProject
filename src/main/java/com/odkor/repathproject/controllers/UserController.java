package com.odkor.repathproject.controllers;

import com.odkor.repathproject.models.Company;
import com.odkor.repathproject.models.User;
import com.odkor.repathproject.repositories.CompanyRepository;
import com.odkor.repathproject.repositories.UserRepository;
import com.odkor.repathproject.services.UserService;
import com.odkor.repathproject.utils.Utils;
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
    private UserService userService;

    /**
     * GET /getAllUsers : Get all users from the connected database.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of users in
     * the body, or with status 204 (NO CONTENT) if there are no users in
     * database, or with status 500 (INTERNAL SERVER ERROR) if process failed
     * to complete for any other reason.
     *
     */
    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers() {

        logger.info("Attempting to retrieve users from database...");

        try {
            List<User> allUsers = (List<User>) userService.findAllUsers();

            if(allUsers.isEmpty()) {
                logger.info("Attempt was successful, but list of users was empty.");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            logger.info("Attempt was successful, list of users was returned.");
            return new ResponseEntity<>(allUsers, HttpStatus.OK);
        } catch (Exception ex) {
            String exceptionString = Utils.getExceptionStackTrace(ex);
            logger.info("Attempt was unsuccessful due to exception: " + exceptionString);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * POST /addUser : Add a user to the connected database.
     *
     * @return the ResponseEntity with status 200 (OK)if the user was added,
     * or with status 500 (INTERNAL SERVER ERROR) if process failed
     * to complete for any other reason.
     *
     */
    @PostMapping(value = "/addUser", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> createUser(@RequestBody User user) {

        logger.info("Attempting to create new user with info " + user.toString());

        try {
            User userToBeCreated = new User();
            userToBeCreated.setName(user.getName());
            userToBeCreated.setEmail(user.getEmail());
            userToBeCreated.setCompanyId(user.getCompanyId());
            userService.saveUser(userToBeCreated);

            logger.info("Created user successfully.");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            String exceptionString = Utils.getExceptionStackTrace(ex);
            logger.info("Could not create new user due to exception: " + exceptionString);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * DELETE /deleteUserById : Delete a user from the connected database
     * based on ID.
     *
     * @param id the id of the user to be deleted
     * @return the ResponseEntity with status 200 (OK) if the user was deleted,
     * or with status 500 (INTERNAL SERVER ERROR) if process failed
     * to complete for any other reason.
     *
     */
    @DeleteMapping(value = "/deleteUser/{userId}")
    public ResponseEntity<String> deleteUserById(@PathVariable("userId") Long id) {

        logger.info("Attempting to delete user with id " + id);

        try {

            if(isNull(userService.findUserById(id))) {
                logger.info("Selected user does not exist, update cannot continue.");
                throw new Exception("User does not exist in DataBase.");
            }

            userService.deleteUserById(id);;

            logger.info("User was deleted successfully.");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            String exceptionString = Utils.getExceptionStackTrace(ex);
            logger.info("Could not delete user due to exception: " + exceptionString);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * DELETE /getUsersByCompany : Retrieve all users matching a company id
     * from the connected database.
     *
     * @param id the id of the company
     * @return the ResponseEntity with status 200 (OK) and the list of users in
     * the body, or with status 204 (NO CONTENT) if there are no users matching the criteria
     * in the database, or with status 500 (INTERNAL SERVER ERROR) if process failed
     * to complete for any other reason.
     *
     */
    @GetMapping("/getUsersByCompany/{companyId}")
    public ResponseEntity<List<User>> getUsersByCompany(@PathVariable("companyId") Long id) {
        logger.info("Attempting to retrieve users from database based ond company...");

        try {
            List<User> users = (List<User>) userService.findUsersByCompany(id);

            if(users.isEmpty()) {
                logger.info("Attempt was successful, but list of users was empty.");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            logger.info("Attempt was successful, list of users was returned.");
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception ex) {
            String exceptionString = Utils.getExceptionStackTrace(ex);
            logger.info("Attempt was unsuccessful due to exception: " + exceptionString);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * POST /addUser : Update a user from the connected database.
     *
     * @return the ResponseEntity with status 200 (OK)if the user was updated,
     * or with status 500 (INTERNAL SERVER ERROR) if process failed
     * to complete because user did not exist or for any other reason.
     *
     */
    @PutMapping(value = "/updateUser", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> updateUser(@RequestBody User user) {

        Long id = user.getId();
        logger.info("Attempting to update user with id " + id);

        try {

            if(isNull(userService.findUserById(id))) {
                logger.info("Selected user does not exist, update cannot continue.");
                throw new Exception("User does not exist in DataBase.");
            }

            User updatedUser = new User();
            updatedUser.setId(user.getId());
            updatedUser.setName(user.getName());
            updatedUser.setEmail(user.getEmail());
            updatedUser.setCompanyId(user.getCompanyId());

            userService.saveUser(user);

            logger.info("Updated user successfully.");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            String exceptionString = Utils.getExceptionStackTrace(ex);
            logger.info("Could not create new user due to exception: \n" + exceptionString);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
