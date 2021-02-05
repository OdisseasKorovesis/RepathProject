package com.odkor.repathproject.services;

import com.odkor.repathproject.models.Company;
import com.odkor.repathproject.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public void saveCompany(Company company) {
        companyRepository.save(company);
    }
}
