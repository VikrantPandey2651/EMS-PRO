package com.employeemanagementsystem.services.impl;

import com.employeemanagementsystem.customExceptions.exceptions.ModelNotFoundException;
import com.employeemanagementsystem.model.Employee;
import com.employeemanagementsystem.model.LeaveRequest;
import com.employeemanagementsystem.repositories.LeaveRequestRepo;
import com.employeemanagementsystem.services.EmployeeModelService;
import com.employeemanagementsystem.services.LeaveRequestModelService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class LeaveRequestModelServiceImpl implements LeaveRequestModelService {

    @Autowired
    private EmployeeModelService employeeModelService;

    @Autowired
    private LeaveRequestRepo leaveRequestRepo;

    Logger logger = LoggerFactory.getLogger("LeaveRequestModelServiceImpl.class");

    @Transactional
    @Override
    public LeaveRequest createLeaverequest(LeaveRequest leaverequest, Long employeeId) {

        Optional<Employee> emp = employeeModelService.getEmployee(employeeId);
        if(emp.isEmpty()){
            logger.info("No employee found with id while creating leaveRequest"+ employeeId);
            throw new ModelNotFoundException("No employee found with id "+employeeId, HttpStatus.BAD_REQUEST);

        }

        Employee employee = emp.get();
        leaverequest.setEmployee(employee);
        return leaveRequestRepo.save(leaverequest);
    }

    @Override
    public String updateLeaveRequest(Long id, LeaveRequest leaverequest) {
        return null;
    }

    @Override
    public String deleteLeaveRequest(LeaveRequest leaveRequest) {
        return null;
    }

    @Override
    public String deleteLeaveRequest(Long leaveRequestId) {
        return null;
    }

    @Override
    public Optional<LeaveRequest> getLeaveRequest(Long id) {
        return Optional.empty();
    }

    @Override
    public List<LeaveRequest> getAllLeaveRequest() {
        return null;
    }

    @Override
    public List<LeaveRequest> findRequestByEmployee(Long employeeId)
    {
        Optional<Employee> emp = employeeModelService.getEmployee(employeeId);
        if(emp.isEmpty()){
            logger.info("No employee found with id while creating attendance"+ employeeId);
            throw new ModelNotFoundException("No employee found with id "+employeeId, HttpStatus.BAD_REQUEST);

        }
        return leaveRequestRepo.findByEmployeeEmployeeId(employeeId);
    }
}
