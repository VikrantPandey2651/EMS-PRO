package com.employeemanagementsystem.services.impl;


import com.employeemanagementsystem.customExceptions.exceptions.DuplicateValuePresentException;
import com.employeemanagementsystem.customExceptions.exceptions.GenericExceptionInModel;
import com.employeemanagementsystem.customExceptions.exceptions.ModelNotFoundException;
import com.employeemanagementsystem.model.Designation;
import com.employeemanagementsystem.model.Employee;
import com.employeemanagementsystem.repositories.EmployeeRepo;
import com.employeemanagementsystem.services.EmployeeModelService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import javax.swing.text.html.Option;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmployeeModelServiceImpl implements EmployeeModelService {

    Logger logger = LoggerFactory.getLogger(EmployeeModelService.class);

    @Autowired
    private EmployeeRepo employeeRepo;


    @Transactional
    @Override
    public Employee createEmployee(Employee employee) {
        if(employeeRepo.existsByEmail(employee.getEmail())){
                throw new DuplicateValuePresentException("Email already exists " + employee.getEmail(),HttpStatus.BAD_REQUEST);
            }

            return employeeRepo.save(employee);


    }

    @Transactional
    @Override
    public String updateEmployee(Long id, Employee employee) {
        Optional<Employee> optionalEmployee = employeeRepo.findById(id);
        if(optionalEmployee.isPresent()){
            Employee existingEmployee = optionalEmployee.get();
        }
        return "Employee updated";

    }


    @Transactional
    @Override
    public String deleteEmployee(Long employeeId) {
       Optional<Employee> emp = employeeRepo.findById(employeeId);

       if(emp.isEmpty()){
           throw new ModelNotFoundException("Employee not found with ID: " + employeeId, HttpStatus.NOT_FOUND);
       }

       Employee employee = emp.get();

       try{
           employeeRepo.delete(employee);
           logger.info("Employee deleted successfully with id : " + employeeId);
           return "Employee deleted successfully";
       }catch(Exception ex){
           logger.info("Employee can not be deleted successfully with id : " + employeeId);
           throw new GenericExceptionInModel("Can not perform the given operation");
        }
    }

    @Override
    public Optional<Employee> getEmployee(Long id) {
        return employeeRepo.findById(id);
    }

    @Override
    public Page<Employee> getAllEmployees(int page, int size) {
        Pageable pageable = PageRequest.of(page,size, Sort.by("firstName"));

        return employeeRepo.findAll(pageable);
    }

    @Transactional
    @Override
    public String addManagerToEmployee(Long empId, Long managerId){
        Optional<Employee> emp = employeeRepo.findById(empId);

        if(emp.isEmpty()){
            throw new ModelNotFoundException("Employee not found with ID: " + empId, HttpStatus.NOT_FOUND);
        }

        Optional<Employee> manager1 = employeeRepo.findById(managerId);

        if(manager1.isEmpty()){
            throw new ModelNotFoundException("Manager not found with ID: " + managerId, HttpStatus.NOT_FOUND);
        }

        Employee employee = emp.get();
        Employee manager = manager1.get();

        if(!Objects.equals(manager.getRole().getRole(), "MANAGER")){
            throw new GenericExceptionInModel("Inappropriate manager "+manager.getEmployeeId());
        }

        employee.setManager(manager);
        return "SuccessFully added manager ";
    }

    @Override
    public Optional<Employee> findEmployeeByUsername(String username) {
        Optional<Employee> employee = employeeRepo.findByUsername(username);
        if(employee.isEmpty()){
            throw new GenericExceptionInModel("No user with this username is present "+username);
        }
        return employee;
    }


}
