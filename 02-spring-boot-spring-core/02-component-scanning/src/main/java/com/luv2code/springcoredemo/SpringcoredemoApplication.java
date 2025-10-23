package com.luv2code.springcoredemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//If coach and CricketCoach classes are in the util package, they are outside of the base packages that are scanned.
//@SpringBootApplication(
//        scanBasePackages = {"com.luv2code.springcoredemo",
//                            "com.luv2code.util"})
@SpringBootApplication
public class SpringcoredemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcoredemoApplication.class, args);
	}

}
