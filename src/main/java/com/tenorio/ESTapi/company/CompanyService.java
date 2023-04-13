package com.tenorio.ESTapi.company;

import org.springframework.stereotype.Service;

@Service
public class CompanyService {
    CompanyRepository companyRepository;

    public CompanyService (CompanyRepository companyRepository) {this.companyRepository = companyRepository;}

     public void getCompany(Integer id)
    {
        companyRepository.findAll();
    }

    public void addCompany(Company company){
        companyRepository.save(company);
    }
}
