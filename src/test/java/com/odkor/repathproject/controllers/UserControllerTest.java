package com.odkor.repathproject.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.odkor.repathproject.models.Company;
import com.odkor.repathproject.models.User;
import com.odkor.repathproject.repositories.CompanyRepository;
import com.odkor.repathproject.repositories.UserRepository;
import com.odkor.repathproject.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.internal.matchers.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.ArrayList;
import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.any;
import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {


    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllUsers_Success() throws Exception {
        Company company = new Company();
        company.setName("TestComp");

        User user = new User();
        user.setName("TestUser");
        user.setEmail("testmailgmail.com");
        user.setCompanyId(company);

        List<User> allUsers = new ArrayList<>();
        allUsers.add(user);

        given(userService.findAllUsers()).willReturn(allUsers);

        mockMvc.perform(get("/getAllUsers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name").value(user.getName()));
    }

    @Test
    public void getAllUsers_EmptyList() throws Exception {

        List<User> allUsers = new ArrayList<>();

        given(userService.findAllUsers()).willReturn(allUsers);

        mockMvc.perform(get("/getAllUsers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void getAllUsers_Error() throws Exception {

        given(userService.findAllUsers()).willThrow(NullPointerException.class);

        mockMvc.perform(get("/getAllUsers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void deleteUserById_Success() throws Exception {

        Company company = new Company();
        company.setName("TestComp");

        User user = new User();
        user.setName("TestUser");
        user.setEmail("testmailgmail.com");
        user.setCompanyId(company);

        given(userService.findUserById(1L)).willReturn(user);

        mockMvc.perform(delete("/deleteUser/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteUserById_NotExistentUserOrOtherError() throws Exception {

        mockMvc.perform(delete("/deleteUser/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void createUser_Success() throws Exception {
        Company company = new Company();
        company.setName("TestComp");

        User user = new User();
        user.setName("TestUser");
        user.setEmail("testmailgmail.com");
        user.setCompanyId(company);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter writer = mapper.writer().withDefaultPrettyPrinter();
        String requestBodyJson = writer.writeValueAsString(user);

        mockMvc.perform(post("/addUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBodyJson))
                .andExpect(status().isOk());
    }

    @Test
    public void createUser_Error() throws Exception {
        Company company = new Company();
        company.setName("TestComp");

        User user = new User();
        user.setName("TestUser");
        user.setEmail("testmailgmail.com");
        user.setCompanyId(company);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter writer = mapper.writer().withDefaultPrettyPrinter();
        String requestBodyJson = writer.writeValueAsString(user);

        doThrow(NullPointerException.class).when(userService).saveUser(user);

        mockMvc.perform(post("/addUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBodyJson))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void getAllUsersByCompany_Success() throws Exception {
        Company company = new Company();
        company.setName("TestComp");

        User user = new User();
        user.setName("TestUser");
        user.setEmail("testmailgmail.com");
        user.setCompanyId(company);

        List<User> usersByCompany = new ArrayList<>();
        usersByCompany.add(user);

        given(userService.findUsersByCompany(1L)).willReturn(usersByCompany);

        mockMvc.perform(get("/getUsersByCompany/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name").value(user.getName()));
    }

    @Test
    public void getAllUsersByCompany_EmptyList() throws Exception {

        List<User> usersByCompany = new ArrayList<>();

        given(userService.findUsersByCompany(1L)).willReturn(usersByCompany);

        mockMvc.perform(get("/getUsersByCompany/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void getAllUsersByCompany_Error() throws Exception {

        given(userService.findUsersByCompany(1L)).willThrow(NullPointerException.class);

        mockMvc.perform(get("/getUsersByCompany/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void updateUser_Success() throws Exception {
        Company company = new Company();
        company.setName("TestComp");

        User user = new User();
        user.setId(1L);
        user.setName("TestUser");
        user.setEmail("testmailgmail.com");
        user.setCompanyId(company);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter writer = mapper.writer().withDefaultPrettyPrinter();
        String requestBodyJson = writer.writeValueAsString(user);

        given(userService.findUserById(anyLong())).willReturn(user);

        mockMvc.perform(put("/updateUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBodyJson))
                .andExpect(status().isOk());
    }

    @Test
    public void updateUser_NotExistentUserOrOtherError() throws Exception {
        Company company = new Company();
        company.setName("TestComp");

        User user = new User();
        user.setId(1L);
        user.setName("TestUser");
        user.setEmail("testmailgmail.com");
        user.setCompanyId(company);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter writer = mapper.writer().withDefaultPrettyPrinter();
        String requestBodyJson = writer.writeValueAsString(user);

        given(userService.findUserById(anyLong())).willReturn(null);

        mockMvc.perform(put("/updateUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBodyJson))
                .andExpect(status().isInternalServerError());
    }
}
