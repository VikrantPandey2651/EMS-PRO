package com.employeemanagementsystem.repositories;

import com.employeemanagementsystem.model.Attendance;
import com.employeemanagementsystem.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface AttendanceRepo extends JpaRepository<Attendance, Long> {

    Optional<List<Attendance>> findByEmployeeEmployeeId(Long employeeId);

    Optional<Attendance>  findByEmployeeEmployeeIdAndDate(Long id, LocalDate date);
}


