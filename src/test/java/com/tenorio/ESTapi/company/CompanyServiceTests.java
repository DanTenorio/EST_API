package com.tenorio.ESTapi.company;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CompanyServiceTests {
    @Mock
    private CompanyRepository companyRepository;
    @InjectMocks
    private CompanyService companyService;


    @Test
    void addCompany(){
        when(companyRepository.save(any(Company.class))).then(returnsFirstArg());
        Company company = new Company("VC");
        Company savedCompany =companyService.addCompany(company);
        assertEquals("VC", company.getName());
    }
}
