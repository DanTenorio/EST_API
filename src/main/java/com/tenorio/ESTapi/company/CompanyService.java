package com.tenorio.ESTapi.company;

import com.tenorio.ESTapi.employee.Employee;
import com.tenorio.ESTapi.employee.EmployeeRepository;
import com.tenorio.ESTapi.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final EmployeeRepository employeeRepository;

     public CompanyDTO getCompany(Integer id)
    {
        if(companyRepository.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Company with ID: " + id + " not found!");
        }
        Company company = companyRepository.findById(id).get();
        List<Employee> employees = employeeRepository.findByCompanyId(id);

        return new CompanyDTO(id, company.getName(), employees);
    }

    public Optional<Company> getCompanyObject(Integer id) {
        return companyRepository.findById(id);
    }

    public List<CompanyDTO> getAllCompanies() {
        List<CompanyDTO> companyDTOS = new ArrayList<>();
        List<Company> companies = companyRepository.findAll();
        for (Company company:
             companies) {
            companyDTOS.add(new CompanyDTO(company.getId(),
                    company.getName(),
                    employeeRepository.findByCompanyId(company.getId())));
        }
        return companyDTOS;
    }

    public CompanyDTO addCompany(Company company){
        companyRepository.save(company);
        return new CompanyDTO(company.getId(), company.getName(), new ArrayList<>());
    }
    public CompanyDTO addCompany(Company company, List<Employee> employees){
        companyRepository.save(company);

        for (Employee employee:
                employees) {
            employee.setCompany(company);
        }
        employeeRepository.saveAll(employees);

        return new CompanyDTO(company.getId(), company.getName(), employees);
    }

    public CompanyDTO updateCompanyName(Integer companyID, String newCompanyName) {
        if(companyRepository.findById(companyID).isEmpty()){
            throw new ResourceNotFoundException("Company with ID: " + companyID + " not found!");
        }
         Company savedCompany = companyRepository.findById(companyID).get();
         savedCompany.setName(newCompanyName);
         companyRepository.save(savedCompany);
         return new CompanyDTO(savedCompany.getId(), savedCompany.getName(), employeeRepository.findByCompanyId(companyID));

    }

    public CompanyDTO updateEmployeeName(Integer companyID, Integer employeeID, String newName) {
        Employee employee = employeeRepository.findById(employeeID).get();
        System.out.println(employee);
        Company company = companyRepository.findById(companyID).get();

        if(company == null){
            throw new ResourceNotFoundException("Company with ID: " + companyID + " not found!");
        }
        if(employeeRepository.findById(employeeID) == null){
            throw new ResourceNotFoundException("Employee with ID: " + employeeID + " not found!");
        }
        employee.setEmployeeName(newName);
        System.out.println(employee);
        Employee savedEmployee = employeeRepository.save(employee);
        System.out.println(savedEmployee);
        return new CompanyDTO(companyID, company.getName(), employeeRepository.findByCompanyId(companyID));
    }

    public CompanyDTO addEmployee(Integer id, Employee employee) {
         Company company = companyRepository.findById(id).get();
         employee.setCompany(company);
         employeeRepository.save(employee);
         return new CompanyDTO(id , company.getName(), employeeRepository.findByCompanyId(id));
    }

    public void deleteCompany(Integer companyID) {
        if(companyRepository.findById(companyID).isEmpty()){
            throw new ResourceNotFoundException("Company with ID: " + companyID + " not found!");
        }
        companyRepository.deleteById(companyID);
    }

    public void deleteEmployee(Integer companyID, Integer employeeID) {
         Employee employee = employeeRepository.findById(employeeID).get();
         if(companyRepository.findById(companyID).isEmpty()){
             throw new ResourceNotFoundException("Company with ID: " + companyID + " not found!");
         }
         if(employee == null){
             throw new ResourceNotFoundException("Employee with ID: " + employeeID + " not found!");
         }
         employeeRepository.deleteById(employeeID);
    }



}
