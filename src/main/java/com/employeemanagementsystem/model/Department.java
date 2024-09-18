package com.employeemanagementsystem.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long departmentId;

    @NotNull(message = "Department name should not be empty")
    @Column(unique = true)
    private String departmentName;



    @OneToOne
    @JoinColumn(name="employee_id")
    private Employee departmentHead;

    @OneToMany(mappedBy = "department")
    private Set<Employee> employees;
}
