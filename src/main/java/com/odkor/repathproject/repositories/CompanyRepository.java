package com.odkor.repathproject.repositories;

import com.odkor.repathproject.models.Company;
import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository<Company, Long> {
}
