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
import com.learning.oneworforce.model.Timesheet;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TimesheetTest {



	
	@LocalServerPort
	private int port;
	@Autowired
	private TestRestTemplate restTemplate;
	

	Timesheet timesheet = new Timesheet();
	
	@Test
	public void testPerformance()
	{
		
		try
		{
			
			String dateInString = "26-Aug-2020";
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
			 Date date = formatter.parse(dateInString);
			 
			 timesheet.setApproval_date(dateInString);
			 timesheet.setApprover_comments("test");
			 timesheet.setApprover_id("1");
			 timesheet.setCreated_date(dateInString);
			 timesheet.setEmp_no("1");
			 timesheet.setEmpname("test");
			 timesheet.setStatus("test");
			 timesheet.setTasks("test");
			 timesheet.setTimesheet_date(dateInString);
			 timesheet.setTimesheet_hours("1");
			 timesheet.setTimesheet_id("1");
			 
				
		        Assert.assertEquals(dateInString ,timesheet.getApproval_date());
		        Assert.assertEquals("test" ,timesheet.getApprover_comments());
		        Assert.assertEquals("1" ,timesheet.getApprover_id());
		        Assert.assertEquals(dateInString ,timesheet.getCreated_date());
		        Assert.assertEquals("1" ,timesheet.getEmp_no());
		        Assert.assertEquals("test" ,timesheet.getEmpname());
		        Assert.assertEquals("test" ,timesheet.getStatus());
		        Assert.assertEquals("test" ,timesheet.getTasks());
		        Assert.assertEquals(dateInString ,timesheet.getTimesheet_date());
		        Assert.assertEquals("1" ,timesheet.getTimesheet_hours());
		        Assert.assertEquals("1" ,timesheet.getTimesheet_id());
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
	}
	


}
