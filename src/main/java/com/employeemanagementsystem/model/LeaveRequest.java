package com.employeemanagementsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaveRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long leaveRequestId;

    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;

    private boolean isApproved = false;


    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
