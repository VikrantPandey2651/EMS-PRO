package com.employeemanagementsystem.services.securityService;


import com.employeemanagementsystem.model.Employee;
import com.employeemanagementsystem.services.EmployeeModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomSecurityService implements UserDetailsService {

    @Autowired
    private EmployeeModelService employeeModelService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Employee> optionalEmployee = employeeModelService.findEmployeeByUsername(username);
        if(optionalEmployee.isEmpty()){
            throw new UsernameNotFoundException("No user present with given name..."+username);
        }
        Employee employee = optionalEmployee.get();
        return  org.springframework.security.core.userdetails.User.builder()
                .username(employee.getUsername()) // Use email as the username
                .password(employee.getPassword()) // Use user's password
                .roles(String.valueOf(employee.getRole())) // Set user's roles
                .build();
    }
}
