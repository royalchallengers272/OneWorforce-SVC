package com.learning.oneworforce.dao;


import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import com.learning.oneworforce.model.Employee;
import com.learning.oneworforce.model.Leave;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Repository
public class MySQLDAO {

	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	

	
	private static final Logger LOGGER = LoggerFactory.getLogger(MySQLDAO.class);
	
	public List<Employee> getAllEmployeeData(){
		

		
		String shiptocountry=null;
		DataSource dataSource;
		Connection connection=null;
		List<Employee> employeedetails = new ArrayList<>();
		//String selectquery="select top 1 SHIP_TO_COUNTRY from  LEGAL_DB.LEGAL_DFI_BR.BV_CMPL_PLT_SERIAL_NUMBER  where LOWER_BP_SERIAL_NUMBER=? and CARTON_ID=? and LOWER_ITEM_SERIAL_NUMBER=?";
		String selectquery="select * from employees";
	
			try
		{
				  long startTime = System.nanoTime();
			dataSource = jdbcTemplate.getDataSource();
			connection = null;
			if(null == dataSource){
				
			}

			connection = dataSource.getConnection();
			
			if(null == connection){
			
			}
			


	                 employeedetails=jdbcTemplate.query(
			    			selectquery,
			                (rs, rowNum) ->
			                        new Employee(
			                                rs.getInt("emp_no"),
			                                rs.getString("birth_date"),
			                                rs.getString("first_name"),
			                                rs.getString("last_name"),
			                                rs.getString("gender"),
			                                rs.getString("hire_date")
			                        ));
			    	
			
			 
		}
		   catch (EmptyResultDataAccessException e) {
			   LOGGER.error(e.getMessage());
			   LOGGER.error("An error occurred while getting  employees info.", e);
	   } 
		   catch (SQLException e) {
			   LOGGER.error(e.getMessage());
				LOGGER.error("An error occurred while getting employees info.", e);
				
			
		}
			finally
			{
				try {
					connection.close();
				   } 
				catch (SQLException e) {
					
					LOGGER.error(e.getMessage());
					LOGGER.error("An error occurred while closing connection.", e);
					
					//e.printStackTrace();
				}
			}
			
			 return employeedetails;
	
				}
	
public List<Employee> getEmployeeData(String emp_no){
		


		DataSource dataSource;
		Connection connection=null;
		List<Employee> employeedetails = new ArrayList<>();
		//String selectquery="select top 1 SHIP_TO_COUNTRY from  LEGAL_DB.LEGAL_DFI_BR.BV_CMPL_PLT_SERIAL_NUMBER  where LOWER_BP_SERIAL_NUMBER=? and CARTON_ID=? and LOWER_ITEM_SERIAL_NUMBER=?";
		String selectquery="select * from employees where emp_no="+emp_no;
	
			try
		{
				  long startTime = System.nanoTime();
			dataSource = jdbcTemplate.getDataSource();
			connection = null;
			if(null == dataSource){
				
			}

			connection = dataSource.getConnection();
			
			if(null == connection){
			
			}
			
			long endTime = System.nanoTime();
			  System.out.println("Time taken to establish MySQL connection "+(endTime-startTime)/1000000);

	                 employeedetails=jdbcTemplate.query(
			    			selectquery,
			                (rs, rowNum) ->
			                        new Employee(
			                                rs.getInt("emp_no"),
			                                rs.getString("birth_date"),
			                                rs.getString("first_name"),
			                                rs.getString("last_name"),
			                                rs.getString("gender"),
			                                rs.getString("hire_date")
			                        ));
			    	
		}
		   catch (EmptyResultDataAccessException e) {
			   LOGGER.error(e.getMessage());
			   LOGGER.error("An error occurred while getting  employees info.", e);
	   } 
		   catch (SQLException e) {
			   LOGGER.error(e.getMessage());
				LOGGER.error("An error occurred while getting employees info.", e);
				
			
		}
			finally
			{
				try {
					connection.close();
				   } 
				catch (SQLException e) {
					
					LOGGER.error(e.getMessage());
					LOGGER.error("An error occurred while closing connection.", e);
					
				}
			}
			
			 return employeedetails;
	
				}

public int insertleave(Leave leave)
{
	try{
	 String INSERT_QUERY = "insert into leaves (leave_type,emp_no,created_date,from_date,to_date,reason,approval_status,approver_id,approval_date) values (?, ?,?, ?,?, ?,?, ?,?)";
	 
	  
	    
	    Date fromdate=new SimpleDateFormat("dd/MM/yyyy").parse(leave.getFrom_date());  
	    Date todate=new SimpleDateFormat("dd/MM/yyyy").parse(leave.getTo_date()); 
	 //return jdbcTemplate.update(INSERT_QUERY, leave.getLeave_type(),leave.getEmp_no(),leave.getCreated_date(),leave.getFrom_date(),leave.getTo_date(),leave.getReason(),leave.getApproval_status(),leave.getApprover_id(),leave.getApproval_date());
	 return jdbcTemplate.update(INSERT_QUERY, leave.getLeave_type(),leave.getEmp_no(),new Date(),fromdate,todate,leave.getReason(),leave.getApproval_status(),null,null);
	}
	catch(Exception e)
	{
		System.out.println("Exception while submitting leave"+e);
		return 0;
		
}
	
}

public int updateleave(Leave leave)
{
	try{
		
	 String UPDATE_QUERY  = "update leaves set approval_status=?,approver_id=?,approvercomments=? where leave_id=?";
	 
	 //return jdbcTemplate.update(INSERT_QUERY, leave.getLeave_type(),leave.getEmp_no(),leave.getCreated_date(),leave.getFrom_date(),leave.getTo_date(),leave.getReason(),leave.getApproval_status(),leave.getApprover_id(),leave.getApproval_date());
	 return jdbcTemplate.update(UPDATE_QUERY , leave.getApproval_status(),leave.getApprover_id(),leave.getApprovercomments(),Integer.parseInt(leave.getLeave_id()));
	}
	catch(Exception e)
	{
		System.out.println("Exception while Updating leave"+e);
		return 0;
		
}
	
}

public  List <Leave>getleaves(String empno, String status)
{

	


	DataSource dataSource;
	Connection connection=null;
	List<Leave> leavedetails = new ArrayList<>();
	//String selectquery="select top 1 SHIP_TO_COUNTRY from  LEGAL_DB.LEGAL_DFI_BR.BV_CMPL_PLT_SERIAL_NUMBER  where LOWER_BP_SERIAL_NUMBER=? and CARTON_ID=? and LOWER_ITEM_SERIAL_NUMBER=?";
	String selectquery="select * from leaves where emp_no=? and approval_status=?";
	
	System.out.println(selectquery);

		try
	{
			  long startTime = System.nanoTime();
		dataSource = jdbcTemplate.getDataSource();
		connection = null;
		if(null == dataSource){
			
		}

		connection = dataSource.getConnection();
		
		if(null == connection){
		
		}
		
		long endTime = System.nanoTime();
		  System.out.println("Time taken to establish MySQL connection "+(endTime-startTime)/1000000);
		
		  leavedetails=jdbcTemplate.query(
		    			selectquery,
		    			  new Object[] { empno, status }, 
		                (rs, rowNum) ->
		                        new Leave(
		                                rs.getString("leave_id"),
		                                rs.getInt("emp_no"),
		                                rs.getString("created_date"),
		                                rs.getString("leave_type"),
		                                rs.getString("from_date"),
		                                rs.getString("to_date"),
		                                rs.getString("approval_status"),
		                                rs.getString("reason"),
		                                rs.getString("approval_date"),
		                                rs.getInt("approver_id")
		                        ));
		    	
	}
	   catch (EmptyResultDataAccessException e) {
		   LOGGER.error(e.getMessage());
		   LOGGER.error("An error occurred while getleaves.", e);
   } 
	   catch (SQLException e) {
		   LOGGER.error(e.getMessage());
			LOGGER.error("An error occurred while getleaves", e);
			
		
	}
		finally
		{
			try {
				connection.close();
			   } 
			catch (SQLException e) {
				
				LOGGER.error(e.getMessage());
				LOGGER.error("An error occurred while closing connection.", e);
				
			}
		}
		
		 return leavedetails;
	
}
}
