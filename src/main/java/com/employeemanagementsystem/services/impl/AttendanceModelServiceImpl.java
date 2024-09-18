package com.employeemanagementsystem.services.impl;

import com.employeemanagementsystem.controller.HomeController;
import com.employeemanagementsystem.customExceptions.exceptions.ExistingAttendanceException;
import com.employeemanagementsystem.customExceptions.exceptions.ModelNotFoundException;
import com.employeemanagementsystem.customExceptions.exceptions.SundayMondayException;
import com.employeemanagementsystem.model.Attendance;
import com.employeemanagementsystem.model.Employee;
import com.employeemanagementsystem.repositories.AttendanceRepo;
import com.employeemanagementsystem.services.AttendanceModelService;
import com.employeemanagementsystem.services.EmployeeModelService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class AttendanceModelServiceImpl implements AttendanceModelService {

    @Autowired
    private EmployeeModelService employeeModelService;

    @Autowired
    private AttendanceRepo attendanceRepo;

    Logger logger = LoggerFactory.getLogger(AttendanceModelServiceImpl.class);


    @Transactional
    @Override
    public Attendance createAttendance(Attendance attendance, Long employeeId) {
        Optional<Employee> emp = employeeModelService.getEmployee(employeeId);
        if(emp.isEmpty()){
            logger.info("No employee found with id while creating attendance"+ employeeId);
            throw new ModelNotFoundException("No employee found with id "+employeeId, HttpStatus.BAD_REQUEST);

        }
        Employee employee = emp.get();

        Optional<Attendance> existingAttendance =
                attendanceRepo.findByEmployeeEmployeeIdAndDate(employeeId,attendance.getDate());

        if(existingAttendance.isPresent()){
            logger.info("Employee has already marked the attendance for date "+ attendance.getDate());
            throw new ExistingAttendanceException("Employee has already marked the attendance", HttpStatus.BAD_REQUEST);
        }

        DayOfWeek dayOfWeek = attendance.getDate().getDayOfWeek();
        if(dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY){
            logger.info("Employee is marking attendance for weekend ");
            throw new SundayMondayException("Can not mark attendance for sunday or saturday", HttpStatus.BAD_REQUEST);

        }

        Attendance markAttendance = new Attendance();
        markAttendance.setPresent(true);
        markAttendance.setComments(attendance.getComments());
        markAttendance.setDate(attendance.getDate());
        markAttendance.setHours(attendance.getHours());
        markAttendance.setWorkPlace(attendance.getWorkPlace());
        markAttendance.setTask(attendance.getTask());
        markAttendance.setEmployee(employee);

        attendanceRepo.save(markAttendance);
        logger.info("Attendance create for employee "+ employee + " and attendance "+ markAttendance);
        //System.out.println(markAttendance.getTask());

        return markAttendance;
    }

    @Override
    public Attendance updateAttendance(Long id, Attendance updatedAttendance) {
        Optional<Employee> emp = employeeModelService.getEmployee(id);
        if(emp.isEmpty()){
            logger.info("No employee found with id while creating attendance"+ id);
            throw new ModelNotFoundException("No employee found with id "+id, HttpStatus.BAD_REQUEST);

        }
        Employee employee = emp.get();





        DayOfWeek dayOfWeek = updatedAttendance.getDate().getDayOfWeek();
        if(dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY){
            logger.info("Employee is marking attendance for weekend ");
            throw new SundayMondayException("Can not mark attendance for sunday or saturday", HttpStatus.BAD_REQUEST);

        }

        Attendance markAttendance = new Attendance();
        markAttendance.setPresent(updatedAttendance.isPresent());
        markAttendance.setComments(updatedAttendance.getComments());
        markAttendance.setDate(updatedAttendance.getDate());
        markAttendance.setHours(updatedAttendance.getHours());
        markAttendance.setWorkPlace(updatedAttendance.getWorkPlace());
        markAttendance.setTask(updatedAttendance.getTask());
        markAttendance.setEmployee(employee);

        attendanceRepo.save(markAttendance);
        logger.info("Attendance create for employee "+ employee + " and attendance "+ markAttendance);
        //System.out.println(markAttendance.getTask());

        return markAttendance;


    }

    @Override
    public String deleteAttendance(Attendance attendance) {
        return null;
    }


    @Override
    public String deleteAttendance(Long attendanceId) {
        return null;
    }

    @Override
    public Optional<Attendance> getAttendance(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Attendance> getAllAttendance() {
        return null;
    }

    @Override
    public List<Attendance> getAttendanceOfEmployee(Long employeeId) {

        Optional<List<Attendance>> attendancesOfEmployee = attendanceRepo.findByEmployeeEmployeeId(employeeId);
        if(attendancesOfEmployee.isEmpty()){
            throw new ModelNotFoundException("No attendances for the given employee "+employeeId,HttpStatus.BAD_REQUEST);
        }
        return attendancesOfEmployee.get();
    }
}
