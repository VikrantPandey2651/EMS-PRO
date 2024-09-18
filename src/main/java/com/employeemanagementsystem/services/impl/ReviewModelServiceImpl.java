package com.employeemanagementsystem.services.impl;

import com.employeemanagementsystem.customExceptions.exceptions.ModelNotFoundException;
import com.employeemanagementsystem.model.Employee;
import com.employeemanagementsystem.model.Review;
import com.employeemanagementsystem.repositories.ReviewRepo;
import com.employeemanagementsystem.services.EmployeeModelService;
import com.employeemanagementsystem.services.ReviewModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class ReviewModelServiceImpl implements ReviewModelService {

    @Autowired
    private EmployeeModelService employeeModelService;

    @Autowired
    private ReviewRepo reviewRepo;

    @Override
    public Review createReview(Long employeeId, Review review) {
        Optional<Employee> emp = employeeModelService.getEmployee(employeeId);
        if(emp.isEmpty()){
            throw new ModelNotFoundException("No employee found with id "+employeeId, HttpStatus.BAD_REQUEST);
        }

        Employee employee = emp.get();
        System.out.println("employee : "+ employee);

        Review review1 = new Review();
        review1.setReviewDate(LocalDate.now());
        review1.setEmployee(employee);

        review1.setManager(employee.getManager());
        review1.setComments(review.getComments());

        return reviewRepo.save(review1);
    }

    @Override
    public void deleteReview(Long id) {

    }

    @Override
    public Review updateReview(Long id, Review review) {
        return null;
    }

    @Override
    public List<Review> getAllReviews() {
        return null;
    }

    @Override
    public List<Review> getAllReviewsByEmployee(Employee employee) {
        return null;
    }

    @Override
    public List<Review> getAllReviewsByManager(Employee employee) {
        return null;
    }
}
