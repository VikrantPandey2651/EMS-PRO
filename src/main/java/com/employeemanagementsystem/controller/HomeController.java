package com.employeemanagementsystem.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/home")
public class HomeController {

    Logger logger = LoggerFactory.getLogger(HomeController.class);


    @GetMapping("/log")
    public String home(){
        logger.error("Hi I am inside the home endpoint in HomeController");
        return "Hello-world";

    }
}
