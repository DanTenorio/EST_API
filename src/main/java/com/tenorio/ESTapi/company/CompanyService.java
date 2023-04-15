package com.tenorio.ESTapi.company;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;

     public void getCompany(Integer id)
    {
        companyRepository.findAll();
    }

    public Company addCompany(Company company){
        return companyRepository.save(company);
    }
}
