package com.employeemanagementsystem.services;

import com.employeemanagementsystem.model.Employee;
import com.employeemanagementsystem.model.LeaveRequest;

import java.util.List;
import java.util.Optional;

public interface LeaveRequestModelService {

    LeaveRequest createLeaverequest(LeaveRequest leaverequest, Long employeeId);

    String updateLeaveRequest(Long id, LeaveRequest leaverequest);

    String deleteLeaveRequest(LeaveRequest leaveRequest);

    String deleteLeaveRequest(Long leaveRequestId);

    Optional<LeaveRequest> getLeaveRequest(Long id);

    List<LeaveRequest> getAllLeaveRequest();

    List<LeaveRequest> findRequestByEmployee(Long employeeId);
}
