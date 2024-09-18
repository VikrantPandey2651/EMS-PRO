package com.employeemanagementsystem.controller;

import com.employeemanagementsystem.dto.ApiResponse;
import com.employeemanagementsystem.model.Attendance;
import com.employeemanagementsystem.services.AttendanceModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceModelService attendanceModelService;

    @PostMapping("/mark-attendance/{employeeId}")
    public ResponseEntity<ApiResponse<Attendance>> createAttendance(@RequestBody Attendance attendance,
                                                                    @PathVariable Long employeeId){
        Attendance attendance1 = attendanceModelService.createAttendance(attendance,employeeId);
        ApiResponse<Attendance> apiResponse = new ApiResponse<>(
                "Attendance marked successfully ",attendance1, HttpStatus.CREATED);
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);

    }

    @GetMapping("/all-attendances/{employeeId}")
    public ResponseEntity<ApiResponse<List<Attendance>>> getAllAttendancesOfEmployee(@PathVariable
                                                                                     Long employeeId){
        List<Attendance> attendances = attendanceModelService.getAttendanceOfEmployee(employeeId);
        ApiResponse<List<Attendance>> apiResponse = new ApiResponse<>("Fetched all attendances ",attendances, HttpStatus.FOUND);
        return new ResponseEntity<>(apiResponse,HttpStatus.FOUND);
    }
}
