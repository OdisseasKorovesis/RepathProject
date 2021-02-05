package com.odkor.repathproject.controllers;

import com.odkor.repathproject.models.Company;
import com.odkor.repathproject.models.User;
import com.odkor.repathproject.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.ArrayList;
import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void getAllUsers() throws Exception{
        Company company = new Company();
        company.setName("TestComp");

        User user = new User();
        user.setName("TestUser");
        user.setEmail("testmailgmail.com");
        user.setCompanyId(company);

        List<User> allUsers = new ArrayList<>();
        allUsers.add(user);

        when(userService.findAllUsers()).thenReturn(allUsers);

        mockMvc.perform(get("/getAllUsers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect((ResultMatcher) jsonPath("$[0].name", is(user.getName())));
    }
}
