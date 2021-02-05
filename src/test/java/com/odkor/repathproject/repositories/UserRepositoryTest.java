package com.odkor.repathproject.repositories;

import com.odkor.repathproject.models.Company;
import com.odkor.repathproject.models.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findById_Success() {
        Company company = new Company();
        company.setName("TestComp");
        entityManager.persist(company);

        User user = new User();
        user.setName("TestUser");
        user.setEmail("testmailgmail.com");
        user.setCompanyId(company);
        Long id = entityManager.persistAndGetId(user, Long.class);

        User foundUser = userRepository.findUserById(id);
        assertEquals(user, foundUser);
    }

    @Test
    public void findUsersByCompany_Success() {
        Company company = new Company();
        company.setName("TestComp");
        Long id = entityManager.persistAndGetId(company, Long.class);

        List<User> users = new ArrayList<>();

        User user = new User();
        user.setName("TestUser");
        user.setEmail("testmailgmail.com");
        user.setCompanyId(company);
        users.add(user);
        entityManager.persist(user);

        User user2 = new User();
        user2.setName("TestUser");
        user2.setEmail("testmailgmail.com");
        user2.setCompanyId(company);
        users.add(user2);
        entityManager.persist(user2);

        List<User> foundUsers = userRepository.findUsersByCompany(id);

        assertEquals(foundUsers.size(), 2);

        for(int i=0; i<users.size(); i++) {
            assertEquals(users.get(i), foundUsers.get(i));
        }
    }

}
