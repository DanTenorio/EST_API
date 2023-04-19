package com.tenorio.ESTapi.employee;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTests {
    @Mock
    EmployeeRepository employeeRepository;
    @InjectMocks
    EmployeeService employeeService;

    @Test
    public void addEmployee(){
        when(employeeRepository.save(any(Employee.class))).then(returnsFirstArg());
        Employee employee = new Employee("Test Employee");
        Employee savedEmployee = employeeService.addEmployee(employee);
        assertEquals(savedEmployee.getEmployeeName(), employee.getEmployeeName());
    }


}
