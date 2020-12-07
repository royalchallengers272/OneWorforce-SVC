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


import com.learning.oneworforce.model.Performance;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PerformanceTest {
	
	@LocalServerPort
	private int port;
	@Autowired
	private TestRestTemplate restTemplate;
	

	Performance performance = new Performance();
	
	@Test
	public void testPerformance()
	{
		
		try
		{
			
			String dateInString = "26-Aug-2020";
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
			 Date date = formatter.parse(dateInString);
			 
			 
		

			performance.setPerformance_id("test");
			performance.setAccomplishments("test");
			performance.setApproval_date(dateInString);
			performance.setAccomplishments("test");
			performance.setApprover_comments("test");
			performance.setDevelopment_goals("test");
			performance.setEmp_no("1");
			performance.setEmpname("test");
			performance.setResponsibilities("test");
			
		//	notification.setCreateddate(date);
			
			
	        Assert.assertEquals("test" ,performance.getPerformance_id());
	        Assert.assertEquals("test" ,performance.getAccomplishments());
	        Assert.assertEquals(dateInString ,performance.getApproval_date());
	        Assert.assertEquals("test" ,performance.getAccomplishments());
	        Assert.assertEquals("test" ,performance.getApprover_comments());
	        Assert.assertEquals("test" ,performance.getAccomplishments());
	        Assert.assertEquals("test" ,performance.getDevelopment_goals());
	        Assert.assertEquals("1" ,performance.getEmp_no());
	        Assert.assertEquals("test" ,performance.getResponsibilities());
	    	//Assert.assertEquals(date ,notification.getCreateddate());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
	}

}
