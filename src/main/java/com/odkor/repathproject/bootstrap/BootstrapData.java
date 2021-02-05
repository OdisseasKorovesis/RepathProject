package com.odkor.repathproject.bootstrap;

import com.odkor.repathproject.models.Company;
import com.odkor.repathproject.models.User;
import com.odkor.repathproject.repositories.CompanyRepository;
import com.odkor.repathproject.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(BootstrapData.class);
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    public BootstrapData(UserRepository userRepository, CompanyRepository companyRepository) {
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
    }


    //below method runs on application startup and is used to fill
    //the db with some initial data
    @Override
    public void run(String... args) throws Exception {

        logger.info("Loading initial data to database...");

        Company company = new Company();
        company.setName("Repath");
        companyRepository.save(company);

        Company secondCompany = new Company();
        secondCompany.setName("Other Company");
        companyRepository.save(secondCompany);

        User user = new User();
        user.setName("Odisseas");
        user.setEmail("odi@gmail.com");
        user.setCompanyId(company);
        userRepository.save(user);

        User secondUser = new User();
        secondUser.setName("Other User");
        secondUser.setEmail("other@gmail.com");
        secondUser.setCompanyId(secondCompany);
        userRepository.save(secondUser);

        logger.info("Initial data was loaded!");

    }
}
