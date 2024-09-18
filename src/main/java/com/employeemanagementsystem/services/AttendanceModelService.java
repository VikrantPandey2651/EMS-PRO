package com.employeemanagementsystem.services;

import com.employeemanagementsystem.model.Attendance;
import com.employeemanagementsystem.model.Employee;

import java.util.List;
import java.util.Optional;

public interface AttendanceModelService {

    Attendance createAttendance(Attendance attendance, Long employeeId);

    Attendance updateAttendance(Long id, Attendance attendance);

    String deleteAttendance(Attendance attendance);

    String deleteAttendance(Long attendanceId);

    Optional<Attendance> getAttendance(Long id);

    List<Attendance> getAllAttendance();

    List<Attendance> getAttendanceOfEmployee(Long employeeId);
}
