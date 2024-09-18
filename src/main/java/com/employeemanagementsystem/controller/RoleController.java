package com.employeemanagementsystem.controller;

import com.employeemanagementsystem.customExceptions.exceptions.ModelNotFoundException;
import com.employeemanagementsystem.dto.ApiResponse;
import com.employeemanagementsystem.model.Employee;
import com.employeemanagementsystem.model.Role;
import com.employeemanagementsystem.services.EmployeeModelService;
import com.employeemanagementsystem.services.RoleModelService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/role")
@RestController
public class RoleController {

    @Autowired
    private RoleModelService roleModelService;

    @Autowired
    private EmployeeModelService employeeModelService;

    @PostMapping("/create-role")
    public ResponseEntity<ApiResponse<Role>> createRole(@RequestBody Role role){
        roleModelService.createRole(role);
        ApiResponse<Role> apiResponse = new ApiResponse<>("Role Created successfully",role, HttpStatus.CREATED);
        return new ResponseEntity<>(apiResponse,HttpStatus.CREATED);
    }

    @GetMapping("/get-all-roles")
    public ResponseEntity<ApiResponse<List<Role>>> getAllRoles(){
        List<Role> roles = roleModelService.getAllRoles();
        ApiResponse<List<Role>> apiResponse = new ApiResponse<>("Fecthed all roles successfully",roles,HttpStatus.CREATED);
        return new ResponseEntity<>(apiResponse,HttpStatus.CREATED);
    }




    public void setRoleToEmployee(Long employeeId, Long roleId){
      if(employeeModelService.getEmployee(employeeId).isEmpty()){
          throw new ModelNotFoundException("No employee with given id "+ employeeId,HttpStatus.BAD_REQUEST);
      }

      Employee employee = employeeModelService.getEmployee(employeeId).get();
      if(roleModelService.getRole(roleId).isEmpty()){
          throw new ModelNotFoundException("No role with given id "+ roleId, HttpStatus.BAD_REQUEST);
      }
      Role role = roleModelService.getRole(roleId).get();
      employee.setRole(role);
    }
}
