package com.tenorio.ESTapi.company;


import com.tenorio.ESTapi.employee.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/companies")
public class CompanyController {
    CompanyService companyService;

    public CompanyController(CompanyService companyService){this.companyService = companyService;}

    @PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CompanyDTO> addCompany(@RequestBody CompanyDTO request) {
        Company company = new Company();
        company.setName(request.getName());
        CompanyDTO savedCompany;
        if(request.getEmployees() == null ){
            savedCompany = companyService.addCompany(company);
        } else {
            savedCompany = companyService.addCompany(company, request.getEmployees());
        }

        return new ResponseEntity<>(savedCompany, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CompanyDTO>> getAllCompanies() {
        List<CompanyDTO> companyDTOS = companyService.getAllCompanies();
        if(companyDTOS.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>( companyDTOS, HttpStatus.OK);
    }
    @GetMapping("/{companyID}")
    public ResponseEntity<CompanyDTO> getCompanyByID(@PathVariable Integer companyID){
        return new ResponseEntity<>(companyService.getCompany(companyID), HttpStatus.OK);
    }

    @PutMapping("/{companyID}")
    public ResponseEntity<CompanyDTO> updateCompanyName(@PathVariable Integer companyID, @RequestBody Company company){
        CompanyDTO savedCompany = companyService.updateCompanyName(companyID, company.getName());
        return new ResponseEntity<>(savedCompany, HttpStatus.OK);
    }
    @PutMapping("/{companyID}/employees")
    public ResponseEntity<CompanyDTO> addEmployee(@PathVariable Integer companyID, @RequestBody Employee employee){
        CompanyDTO companyDTO = companyService.addEmployee(companyID, employee);
        return new ResponseEntity<>(companyDTO, HttpStatus.OK);
    }

    @PutMapping("/{companyID}/{employeeID}")
    public ResponseEntity<CompanyDTO> updateEmployeeInfo(@PathVariable Integer companyID,
                                                         @PathVariable Integer employeeID,
                                                         @RequestBody Employee employee){
        CompanyDTO companyDTO = companyService.updateEmployeeName(companyID, employeeID, employee.getEmployeeName());
        return new ResponseEntity<>(companyDTO, HttpStatus.OK);
    }


    @DeleteMapping("/{companyID}")
    public ResponseEntity<HttpStatus> deleteCompany(@PathVariable Integer companyID){
        companyService.deleteCompany(companyID);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/{companyID}/{employeeID}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable Integer companyID, @PathVariable Integer employeeID){
        companyService.deleteEmployee(companyID, employeeID);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }




}
