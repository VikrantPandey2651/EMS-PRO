package com.employeemanagementsystem.controller;


import com.employeemanagementsystem.customExceptions.exceptions.ModelNotFoundException;
import com.employeemanagementsystem.dto.ApiResponse;
import com.employeemanagementsystem.model.Employee;
import com.employeemanagementsystem.services.EmployeeModelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {


    @Autowired
    private EmployeeModelService employeeModelService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/create-employee")
    public ResponseEntity<ApiResponse<Employee>> createNewEmployee(@Valid  @RequestBody Employee employee){

        String username = getLoggedInUsername();
        System.out.println(username+ "---> Current User");

        Optional<Employee> optionalEmployee = employeeModelService.findEmployeeByUsername(username);
        if(optionalEmployee.isPresent()){
            Employee currentUser = optionalEmployee.get();
            employee.setHr(currentUser);
        }
        String password = employee.getPassword();
        //Encoding password
        employee.setPassword(passwordEncoder.encode(password));
        Employee newEmployee = employeeModelService.createEmployee(employee);
        ApiResponse<Employee> apiResponse = new ApiResponse<>("User Created successfully", employee, HttpStatus.CREATED);
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @GetMapping("/get-all-employees")
    public ResponseEntity<ApiResponse<Page<Employee>>> getAllEmployees(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size
    ){
        Page<Employee> employeeList = employeeModelService.getAllEmployees(page,size);
        ApiResponse<Page<Employee>> apiResponse = new ApiResponse<>("Successfully Got all the emplloyees",employeeList,HttpStatus.FOUND);
        return new ResponseEntity<>(apiResponse,HttpStatus.FOUND);
    }

    @GetMapping("/get-employee/{employeeId}")
    public ResponseEntity<ApiResponse<Employee>> getEmployeeById(@PathVariable Long employeeId){
        Optional<Employee> employee = employeeModelService.getEmployee(employeeId);
        if(employee.isEmpty()){
            throw new ModelNotFoundException("User with id not found :"+employeeId,HttpStatus.BAD_REQUEST);
        }
        ApiResponse<Employee> apiResponse = new ApiResponse<>("Got user successfully", employee.get(), HttpStatus.FOUND);
        return new ResponseEntity<>(apiResponse,HttpStatus.FOUND);
    }

    @DeleteMapping("delete-employee/{employeeId}")
    public ResponseEntity<ApiResponse<String>> deleteEmployee(@PathVariable Long employeeId){
        employeeModelService.deleteEmployee(employeeId);
        ApiResponse<String> apiResponse = new ApiResponse<>("Employee deleted successfully","Employee deleted with id : "+ employeeId, HttpStatus.OK);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @PostMapping("/add-manager/employee/{employeeId}/manager/{managerId}")
    public ResponseEntity<ApiResponse<String>> addManager(@PathVariable Long employeeId,
                                                            @PathVariable Long managerId){
        String message = employeeModelService.addManagerToEmployee(employeeId,managerId);
        ApiResponse<String> apiResponse = new ApiResponse<>("Manager Added successfully", message, HttpStatus.CREATED);
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }


    //This will simply return the username of the currently logged in user
    public String getLoggedInUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                return ((UserDetails) principal).getUsername();
            } else {
                return principal.toString();
            }
        }
        return null;
    }




}


