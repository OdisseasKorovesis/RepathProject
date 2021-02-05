package com.odkor.repathproject.services;

import com.odkor.repathproject.models.Company;
import com.odkor.repathproject.models.User;
import com.odkor.repathproject.repositories.CompanyRepository;
import com.odkor.repathproject.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class CompanyServiceImplTest {

    @TestConfiguration
    static class CompanyServiceImplTestConfiguration {

        @Bean
        public CompanyService companyService() {
            return new CompanyServiceImpl();
        }
    }

    @Autowired
    private CompanyService companyService;

    @MockBean
    private CompanyRepository companyRepository;

    @Test
    public void saveUser() {
        Company company = new Company();
        company.setName("TestComp");

        when(companyRepository.save(company)).thenReturn(null);
        companyService.saveCompany(company);

        verify(companyRepository, times(1)).save(company);
    }
}
