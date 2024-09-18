package com.employeemanagementsystem.services;

import com.employeemanagementsystem.model.Employee;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface EmployeeModelService {

    Employee createEmployee(Employee employee);

    String updateEmployee(Long id, Employee employee);



    String deleteEmployee(Long employeeId);

    Optional<Employee> getEmployee(Long id);

    Page<Employee> getAllEmployees(int page, int size);

    String addManagerToEmployee(Long employeeId, Long managerid);

    Optional<Employee> findEmployeeByUsername(String username);


}
