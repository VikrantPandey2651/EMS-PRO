package com.employeemanagementsystem.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long employeeId;

    @NotNull(message = "FirstName should not be null")
    private String firstName;
    @NotNull(message= "LastName should not be null")
    private String lastName;

    @NotNull(message="Please enter a valid age")
    private int age;

    @Email(message= "This is a email field")
    @Column(unique = true)
    private String email;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Employee manager;

    @OneToMany(mappedBy = "manager")
    private Set<Employee> employeeManagedByThisEmployee;

    @ManyToOne
    @JoinColumn(name="hr_id")
    private Employee hr;

    @OneToMany(mappedBy = "hr")
    private Set<Employee> employeeManagedByThisHr;

    @NotNull(message = "Please enter valid DOB")
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING) // Store as string in the database
    @Column(name = "designation")
    private Designation designation;

    @Column(unique = true)
    private String username;

    private String password;







    @OneToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToOne
    @JoinColumn(name="department_id")
    private Department department;


    @OneToMany(mappedBy = "employee")
    private Set<Attendance> attendances;

    @OneToMany(mappedBy = "employee")
    private Set<LeaveRequest> leaveRequests;

    @OneToMany(mappedBy = "employee")
    private Set<Review> reviews;






}



