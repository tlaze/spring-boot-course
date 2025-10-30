package com.luv2code.demo.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class DemoRestController {

    // ADd code for the "/helo" endpoint
    @GetMapping("/hello")
    public String sayHello(){
        return "Hello World";
    }

}
