package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.model.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StudentController {

    @Value("${countries}")
    private List<String> countries;

    @Value("${favoriteLanguage}")
    private List<String> favoriteLanguage;

    @GetMapping("/showStudentForm")
    public String showForm(Model theModel) {

        // Create a student object
        Student theStudent = new Student();

        // ADd student object to the model
        theModel.addAttribute("student", theStudent);

        // Add the list of countries to the model
        theModel.addAttribute("countries", countries);

        // Add the list of favorite languages to the model
        theModel.addAttribute("favoriteLanguage", favoriteLanguage);

        return "student-form";
    }

    @PostMapping("/processStudentForm")
    public String processForm(@ModelAttribute("student") Student theStudent){

        System.out.printf("theStudent: " + theStudent.getFirstName() + " " + theStudent.getLastName());
        return "student-confirmation";
    }
}
