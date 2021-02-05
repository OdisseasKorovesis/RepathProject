package com.odkor.repathproject.services;

import com.odkor.repathproject.models.Company;
import com.odkor.repathproject.models.User;
import com.odkor.repathproject.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class UserServiceImplTest {

    @TestConfiguration
    static class UserServiceImplTestConfiguration {

        @Bean
        public UserService userService() {
            return new UserServiceImpl();
        }
    }

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void saveUser() {
        Company company = new Company();
        company.setName("TestComp");

        User user = new User();
        user.setName("TestUser");
        user.setEmail("testmailgmail.com");
        user.setCompanyId(company);

        when(userRepository.save(user)).thenReturn(null);
        userService.saveUser(user);

        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void findAllUsers() {
        List<User> allUsers = new ArrayList<>();

        Company company = new Company();
        company.setName("TestComp");

        User user = new User();
        user.setName("TestUser");
        user.setEmail("testmailgmail.com");
        user.setCompanyId(company);

        allUsers.add(user);

        when(userRepository.findAll()).thenReturn(allUsers);

        List<User> foundUsers = userService.findAllUsers();

        assertEquals(foundUsers, allUsers);
    }

    @Test
    public void findUsersByCompany() {
        List<User> usersByCompany = new ArrayList<>();

        Company company = new Company();
        company.setName("TestComp");

        User user = new User();
        user.setName("TestUser");
        user.setEmail("testmailgmail.com");
        user.setCompanyId(company);

        usersByCompany.add(user);

        when(userRepository.findUsersByCompany(anyLong())).thenReturn(usersByCompany);

        List<User> foundUsers = userService.findUsersByCompany(1L);

        assertEquals(foundUsers, usersByCompany);
    }

    @Test
    public void findUserById() {

        Company company = new Company();
        company.setName("TestComp");

        User user = new User();
        user.setId(1L);
        user.setName("TestUser");
        user.setEmail("testmailgmail.com");
        user.setCompanyId(company);

        when(userRepository.findUserById(anyLong())).thenReturn(user);

        User foundUser = userService.findUserById(user.getId());

        assertEquals(foundUser, user);
    }

    @Test
    public void deleteUserById() {

        doNothing().when(userRepository).deleteById(anyLong());
        userService.deleteUserById(1L);

        verify(userRepository, times(1)).deleteById(1L);
    }

}
