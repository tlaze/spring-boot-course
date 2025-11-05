package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class StudentController {

    public String showForm(Model theModel) {

        // Create a student object
        Student theStudent = new Student();

        // ADd student object to the model
        theModel.addAttribute("student", theStudent);

        return "student-form";
    }
}
