package com.learning.oneworforce;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.learning.oneworforce.controller.LoginController;




@SpringBootApplication
public class OneworforceApplication  {
	public static void main(String[] args) {
		SpringApplication.run(OneworforceApplication.class, args);
	}

}
