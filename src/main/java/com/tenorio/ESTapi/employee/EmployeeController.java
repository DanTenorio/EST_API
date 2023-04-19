package com.tenorio.ESTapi.employee;

import com.tenorio.ESTapi.company.CompanyService;
import com.tenorio.ESTapi.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/")

public class EmployeeController {
    EmployeeService employeeService;

    CompanyService companyService;

    public EmployeeController(EmployeeService employeeService, CompanyService companyService) {
        this.employeeService = employeeService;
        this.companyService = companyService;
    }

    @PostMapping("companies/{companyID}/employees")
    public ResponseEntity<Employee> addEmployee(@PathVariable(value = "companyID") Integer companyID,
                                                @RequestBody Employee employeeRequest) {
        Employee savedEmployee = companyService.getCompanyObject(companyID).map(company -> {
            employeeRequest.setCompany(company);
            return employeeService.addEmployee(employeeRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Company with ID:  "+ companyID + " not found!"));

        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @GetMapping("companies/{companyID}/employees")
    public ResponseEntity<List<Employee>> getAllEmployeesByCompanyID(@PathVariable(value = "companyID") Integer companyID)
    {
        List<Employee> employees = employeeService.getAllEmployeesByCompanyID(companyID);

        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getEmployees(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("employees/{employeeID}")
    public ResponseEntity<Employee> getEmployeeByID(@PathVariable(value = "employeeID") Integer employeeID)
    {
        Employee employee = employeeService.getEmployeeByID(employeeID);

        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PutMapping("employees/{employeeID}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "employeeID") Integer employeeID, @RequestBody Employee employeeRequest){
        Employee employee = employeeService.updateEmployeeName(employeeID, employeeRequest);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @DeleteMapping("employees/{employeeID}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable(value = "employeeID")Integer employeeID){
        employeeService.deleteEmployee(employeeID);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
