package com.learning.oneworforce.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learning.oneworforce.dao.MySQLDAO;
import com.learning.oneworforce.model.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@Autowired
	private MySQLDAO mysqldao;
	


    @RequestMapping("/Home")
    public String displayPLTHomePageNew(ModelMap model,HttpServletRequest req, HttpServletResponse res) {
    	  HttpSession session = req.getSession(false);
    	  System.out.println("Helllo");
  

	      return "home";
    
    }
    

@GetMapping("/api/getEmployeeDetails")
public ResponseEntity<List<Employee>> getEmployeeDetails(@RequestParam String empid) {
  try {
	 
	List<Employee> employee = mysqldao.getEmployeeData(empid);
	  System.out.println("Emp No"+employee.get(0).getEmp_no());
	  System.out.println("Birth date"+employee.get(0).getBirth_date());
	  System.out.println("First name"+employee.get(0).getFirst_name());
	  System.out.println("Last name"+employee.get(0).getLast_name());
	  System.out.println("Gender"+employee.get(0).getGender());
	  System.out.println("Hire date"+employee.get(0).getHire_date());
    if (employee.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    return new ResponseEntity<>(employee, HttpStatus.OK);
		}
   catch (Exception e) {
    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}

@GetMapping("/api/getAllEmployeeDetails")
public ResponseEntity<List<Employee>> getEmployeeDetails() {
  try {
	 
	List<Employee> employee = mysqldao.getAllEmployeeData();

    if (employee.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	  System.out.println("Total Employee Count"+employee.size());
    return new ResponseEntity<>(employee, HttpStatus.OK);
		}
   catch (Exception e) {
    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
    
}
