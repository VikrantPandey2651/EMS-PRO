package com.employeemanagementsystem.repositories;

import com.employeemanagementsystem.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {

    boolean existsByEmail(String email);

    Optional<Employee> findByUsername(String username);
}
