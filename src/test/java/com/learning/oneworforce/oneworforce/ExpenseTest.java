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

import com.learning.oneworforce.model.Expense;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ExpenseTest {
	
	@LocalServerPort
	private int port;
	@Autowired
	private TestRestTemplate restTemplate;
	

	Expense expense = new Expense();
	
	@Test
	public void testExpense()
	{
		
		try
		{
			
			String dateInString = "26-Aug-2020";
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
			 Date date = formatter.parse(dateInString);
			 
			 
		

			 expense.setAmount("1");
			 expense.setApprove_date(dateInString);
			 expense.setApprover_id("1");
			 expense.setCreated_date(dateInString);
			 expense.setDescription("test");
			 expense.setEmp_no("1");
			 expense.setEmpname("test");
			 expense.setExpense_id("1");
			 expense.setExpense_type("test");
			 expense.setStatus("test");
		//	notification.setCreateddate(date);
			
			
	        Assert.assertEquals("1" ,expense.getAmount());
	        Assert.assertEquals(dateInString ,expense.getApprove_date());
	        Assert.assertEquals("1" ,expense.getApprover_id());
	        Assert.assertEquals(dateInString ,expense.getCreated_date());
	        Assert.assertEquals("test" ,expense.getDescription());
	        Assert.assertEquals("1" ,expense.getEmp_no());
	        Assert.assertEquals("test" ,expense.getEmpname());
	        Assert.assertEquals("1" ,expense.getExpense_id());
	        Assert.assertEquals("test" ,expense.getExpense_type());
	        Assert.assertEquals("test" ,expense.getStatus());
	    	//Assert.assertEquals(date ,notification.getCreateddate());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
	}

}
