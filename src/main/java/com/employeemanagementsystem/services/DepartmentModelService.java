package com.employeemanagementsystem.services;

import com.employeemanagementsystem.model.Department;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface DepartmentModelService {

    Department createDepartment(Department department);

    Department getDepartmentById(Long id);

    Page<Department> getAllDepartments(int page, int size);

    Department updateDepartment(Long id, Department department);

    void deleteDepartment(Long id) throws EntityNotFoundException;

}
