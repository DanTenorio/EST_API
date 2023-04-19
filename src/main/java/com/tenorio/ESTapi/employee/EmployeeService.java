package com.tenorio.ESTapi.employee;

import com.tenorio.ESTapi.company.Company;
import com.tenorio.ESTapi.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    public Employee addEmployee(Employee employee){return employeeRepository.save(employee);}

    public List<Employee> getAllEmployeesByCompanyID(Integer companyID)
    {
        return employeeRepository.findByCompanyId(companyID);
    }


    public ResponseEntity<List<Employee>> getAllEmployees(){
        return new ResponseEntity<>(employeeRepository.findAll(), HttpStatus.OK);
    }

    public Employee getEmployeeByID(Integer id)
    {
        return employeeRepository.findById(id).get();
    }

    public Employee updateEmployeeName(Integer id, Employee employeeReq)
    {
        Employee foundEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee with ID: " + id + " not found!"));
        foundEmployee.setEmployeeName(employeeReq.getEmployeeName());


        return employeeRepository.save(foundEmployee);
    }

    public void deleteEmployee(Integer id){
        employeeRepository.deleteById(id);
    }
}
