package com.example.auto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
   
    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard"; // Assuming "dashboard" is the name of your Thymeleaf template
    }
    @GetMapping("/")
    public String index() {
        return "tfo"; // Assuming "dashboard" is the name of your Thymeleaf template
    }
}

