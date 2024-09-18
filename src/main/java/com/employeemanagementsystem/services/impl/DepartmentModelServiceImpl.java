package com.employeemanagementsystem.services.impl;

import com.employeemanagementsystem.customExceptions.exceptions.DuplicateValuePresentException;
import com.employeemanagementsystem.customExceptions.exceptions.ModelNotFoundException;
import com.employeemanagementsystem.model.Department;
import com.employeemanagementsystem.repositories.DepartmentRepo;
import com.employeemanagementsystem.services.DepartmentModelService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentModelServiceImpl implements DepartmentModelService {

    @Autowired
    private DepartmentRepo departmentRepo;


    @Transactional
    @Override
    public Department createDepartment(Department department) {

        if(departmentRepo.existsByDepartmentName(department.getDepartmentName())){
            throw new DuplicateValuePresentException("Already department exists with given name "+ department.getDepartmentName(),HttpStatus.BAD_REQUEST);
        }
        return departmentRepo.save(department);
    }

    @Override
    public Department getDepartmentById(Long id)  {
       return departmentRepo.findById(id).orElseThrow(()->
               new  ModelNotFoundException("department does not exists with id "+ id, HttpStatus.BAD_REQUEST));
    }

    @Override
    public Page<Department> getAllDepartments(int page, int size) {
        Pageable pageable = PageRequest.of(page,size, Sort.by("departmentName"));
        return departmentRepo.findAll(pageable);
    }

    @Override
    public Department updateDepartment(Long id, Department department) {
        return null;
    }


    @Override
    public void deleteDepartment(Long id) {

           Optional<Department> department = departmentRepo.findById(id);
           if(department.isEmpty()){
               throw new ModelNotFoundException("No Department with this id "+id,HttpStatus.BAD_REQUEST  );
           }
           Department department1 = department.get();
           departmentRepo.delete(department1);
    }
}
