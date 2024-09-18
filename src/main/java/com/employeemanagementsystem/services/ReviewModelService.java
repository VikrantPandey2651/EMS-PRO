package com.employeemanagementsystem.services;


import com.employeemanagementsystem.model.Employee;
import com.employeemanagementsystem.model.Review;

import java.util.List;

public interface ReviewModelService {

    Review createReview(Long employeeId, Review review);

    void deleteReview(Long id);

    Review updateReview(Long id, Review review);

    List<Review> getAllReviews();

    List<Review> getAllReviewsByEmployee(Employee employee);

    List<Review> getAllReviewsByManager(Employee employee);


}
