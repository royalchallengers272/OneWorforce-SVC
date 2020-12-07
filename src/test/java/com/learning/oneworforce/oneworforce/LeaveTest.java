package com.learning.oneworforce.oneworforce;
import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import com.learning.oneworforce.model.Leave;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class LeaveTest {


	
	@LocalServerPort
	private int port;
	@Autowired
	private TestRestTemplate restTemplate;
	

	Leave leave = new Leave();
	
	@Test
	public void testPerformance()
	{
		
		try
		{
			
			String dateInString = "26-Aug-2020";
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
			 Date date = formatter.parse(dateInString);
			 
			 
		
			 leave.setApproval_date(dateInString);
			 leave.setApproval_status("test");
			 leave.setApprover_id(1);
			 leave.setApprovercomments("test");
			 leave.setCreated_date(dateInString);
			 leave.setEmp_no(1);
			 leave.setEmpname("test");
			 leave.setFrom_date(dateInString);
			 leave.setLeave_id("1");
			 leave.setLeave_type("Sick Leave");
			 leave.setReason("Fever");
			 leave.setTo_date(dateInString);
		
		//	notification.setCreateddate(date);
			
			
	        Assert.assertEquals(dateInString ,leave.getApproval_date());
	        Assert.assertEquals("test" ,leave.getApproval_status());
	        Assert.assertEquals(dateInString ,leave.getApproval_date());
	        Assert.assertEquals(1 ,leave.getApprover_id());
	        Assert.assertEquals("test" ,leave.getApprovercomments());
	        Assert.assertEquals(dateInString ,leave.getCreated_date());
	        Assert.assertEquals(1 ,leave.getEmp_no());
	        Assert.assertEquals("test" ,leave.getEmpname());
	        Assert.assertEquals(dateInString ,leave.getFrom_date());
	        Assert.assertEquals("1" ,leave.getLeave_id());
	        Assert.assertEquals("Sick Leave" ,leave.getLeave_type());
	        Assert.assertEquals("Fever" ,leave.getReason());
	        Assert.assertEquals(dateInString ,leave.getTo_date());
	    	//Assert.assertEquals(date ,notification.getCreateddate());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
	}
	
}
