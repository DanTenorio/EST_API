package com.tenorio.ESTapi.company;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/company")
public class CompanyController {
    CompanyService companyService;

    public CompanyController(CompanyService companyService){this.companyService = companyService;}


    record NewCompanyRequest (String name) { }
    @PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public void addCompany(@RequestBody NewCompanyRequest request) {
        Company company = new Company();
        company.setName(request.name());
        companyService.addCompany(company);
    }

}
