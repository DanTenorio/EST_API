package com.tenorio.ESTapi.employee;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee save(Employee employee);


    List<Employee> findByCompanyId(Integer id);
}
