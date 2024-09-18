package com.employeemanagementsystem.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attendanceId;

    private LocalDate date;
    private boolean present;
    private String comments;
    private int hours;
    private String task;

    @Enumerated(EnumType.STRING)
    @Column(name = "workPlace")
    private WorkPlace workPlace;

    private boolean isApproved = false;




    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;




}

enum WorkPlace{
    WORK_FROM_HOME,
    WORK_FROM_OFFICE
}


