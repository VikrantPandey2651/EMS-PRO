package com.employeemanagementsystem.repositories;

import com.employeemanagementsystem.model.Employee;
import com.employeemanagementsystem.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepo extends JpaRepository<Review, Long> {

    List<Review> findByEmployee(Employee employee);
}
