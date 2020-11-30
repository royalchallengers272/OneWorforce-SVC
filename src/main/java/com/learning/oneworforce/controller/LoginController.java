package com.learning.oneworforce.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learning.oneworforce.dao.MySQLDAO;
import com.learning.oneworforce.model.Employee;
import com.learning.oneworforce.model.Leave;

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
    
@CrossOrigin(origins = "http://oneworkforcesiteaws.s3-website-us-west-1.amazonaws.com")
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

@CrossOrigin(origins = "http://oneworkforcesiteaws.s3-website-us-west-1.amazonaws.com")
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


@CrossOrigin(origins = "http://oneworkforcesiteaws.s3-website-us-west-1.amazonaws.com")
@GetMapping("/api/updateleave")
public ResponseEntity<String> updateLeaveDetails(@RequestParam String leaveid) {
  try {
	  System.out.println("Update Leave"+leaveid);
	int result=mysqldao.updateleave(leaveid);

	 if(result==1)
	 {
	 return new ResponseEntity<>("Success", HttpStatus.OK);
	 }
	 else
	 {
		 return new ResponseEntity<>("Fail", HttpStatus.OK);
	 }
		}
   catch (Exception e) {
	   return new ResponseEntity<>("Error", HttpStatus.OK);
  }
}


@CrossOrigin(origins = "http://oneworkforcesiteaws.s3-website-us-west-1.amazonaws.com")
@PostMapping("/api/submitleave")
public ResponseEntity<String> insertLeaveDetails(@RequestBody Leave leaveobj) {
  try {
	  System.out.println("Submitting leave for"+leaveobj.getEmp_no());
	int result=mysqldao.insertleave(leaveobj);
	 System.out.println("result"+result);
	 if(result==1)
	 {
	 return new ResponseEntity<>("Success", HttpStatus.OK);
	 }
	 else
	 {
		 return new ResponseEntity<>("Fail", HttpStatus.OK);
	 }
		}
   catch (Exception e) {
	   return new ResponseEntity<>("Error", HttpStatus.OK);
  }
}


@CrossOrigin(origins = "http://oneworkforcesiteaws.s3-website-us-west-1.amazonaws.com")
@GetMapping("/api/getleaves")
public ResponseEntity<List<Leave>> getLeaveDetails(@RequestParam String emp_no) {
  try {
	  System.out.println("Fetch leaves for"+emp_no);
	  List<Leave> leave=mysqldao.getleaves(emp_no,"Pending");
	
	 return new ResponseEntity<>(leave, HttpStatus.OK);
	 

		}
   catch (Exception e) {
	   return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
  }
}
    
}
