package com.employeemanagementsystem.controller;

import com.employeemanagementsystem.dto.ApiResponse;
import com.employeemanagementsystem.model.Review;
import com.employeemanagementsystem.services.ReviewModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewModelService reviewModelService;

    @PostMapping("/create-review/employee-id/{employeeId}")
    public ResponseEntity<ApiResponse<Review>> createReview(@RequestBody Review review,
                                                            @PathVariable Long employeeId){
        System.out.println("Controller review .." + review);
        Review newReview = reviewModelService.createReview(employeeId, review);
        System.out.println("Created review " + newReview);
        ApiResponse<Review> apiResponse = new ApiResponse<>("Success",newReview, HttpStatus.CREATED);
        return new ResponseEntity<>(apiResponse,HttpStatus.CREATED);

    }
}
