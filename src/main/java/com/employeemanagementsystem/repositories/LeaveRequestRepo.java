package com.employeemanagementsystem.repositories;

import com.employeemanagementsystem.model.Attendance;
import com.employeemanagementsystem.model.Employee;
import com.employeemanagementsystem.model.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveRequestRepo extends JpaRepository<LeaveRequest, Long> {

    List<LeaveRequest> findByEmployeeEmployeeId(Long employeeId);
}
