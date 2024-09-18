package com.employeemanagementsystem.repositories;

import com.employeemanagementsystem.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepo extends JpaRepository<Department, Long> {

    boolean existsByDepartmentName(String name);
}
