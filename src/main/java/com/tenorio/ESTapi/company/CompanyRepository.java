package com.tenorio.ESTapi.company;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRepository  extends JpaRepository<Company, Integer> {
    Company save(Company company);

    List<Company> findCompaniesByNameContaining(String name);
}
