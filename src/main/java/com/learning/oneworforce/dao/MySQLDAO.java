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
import com.learning.oneworforce.model.Expense;
import com.learning.oneworforce.model.Leave;
import com.learning.oneworforce.model.Performance;

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
		String selectquery="select emp.*,dept.dept_name from employees emp,dept_emp demp,departments dept where emp.emp_no=demp.emp_no and demp.dept_no=dept.dept_no";
	
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
			                                rs.getString("emp_no"),
			                                rs.getString("birth_date"),
			                                rs.getString("first_name"),
			                                rs.getString("last_name"),
			                                rs.getString("gender"),
			                                rs.getString("hire_date"),
			                                rs.getString("email"),
			                                rs.getString("password"),
			                                rs.getString("address1"),
			                                rs.getString("address2"),
			                                rs.getString("city"),
			                                rs.getString("state"),
			                                rs.getString("zip")
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
		String selectquery="select emp.*,dept.dept_name from employees emp,dept_emp demp,departments dept where emp.emp_no=demp.emp_no and demp.dept_no=dept.dept_no and emp.emp_no="+emp_no;
	
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
			                                rs.getString("emp_no"),
			                                rs.getString("birth_date"),
			                                rs.getString("first_name"),
			                                rs.getString("last_name"),
			                                rs.getString("gender"),
			                                rs.getString("hire_date"),
			                                rs.getString("email"),
			                                rs.getString("password"),
			                                rs.getString("address1"),
			                                rs.getString("address2"),
			                                rs.getString("city"),
			                                rs.getString("state"),
			                                rs.getString("zip")
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


public int updateEmployee(Employee employee)
{
	try{
		
	 String UPDATE_QUERY  = "update employees set email=?,password=?,address1=?,address2=?,city=?,state=?,zip=? where emp_no=?";
	 
	 //return jdbcTemplate.update(INSERT_QUERY, leave.getLeave_type(),leave.getEmp_no(),leave.getCreated_date(),leave.getFrom_date(),leave.getTo_date(),leave.getReason(),leave.getApproval_status(),leave.getApprover_id(),leave.getApproval_date());
	 return jdbcTemplate.update(UPDATE_QUERY , employee.getEmail(),employee.getPassword(),employee.getAddress1(),employee.getAddress2(),employee.getCity(),employee.getState(),employee.getZip(),Integer.parseInt(employee.getEmp_no()));
	}
	catch(Exception e)
	{
		System.out.println("Exception while Updating Employee"+e);
		return 0;
		
}
	
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


public int insertExpense(Expense expense)
{
	try{
	 String INSERT_QUERY = "insert into expense (emp_no,expense_type,description,amount,status,approver_id,created_date,approve_date) values (?, ?,?, ?,?, ?,?, ?)";

	 //return jdbcTemplate.update(INSERT_QUERY, leave.getLeave_type(),leave.getEmp_no(),leave.getCreated_date(),leave.getFrom_date(),leave.getTo_date(),leave.getReason(),leave.getApproval_status(),leave.getApprover_id(),leave.getApproval_date());
	 return jdbcTemplate.update(INSERT_QUERY, Integer.parseInt(expense.getEmp_no()),expense.getExpense_type(),expense.getDescription(),expense.getAmount(),"Pending",null,new Date(),null);
	}
	catch(Exception e)
	{
		System.out.println("Exception while submitting expense"+e);
		return 0;
		
}
	
}

public int updateexpense(Expense expense)
{
	try{
		
	 String UPDATE_QUERY  = "update expense set status=?,approver_id=?,approve_date=? where expense_id=?";
	 
	 //return jdbcTemplate.update(INSERT_QUERY, leave.getLeave_type(),leave.getEmp_no(),leave.getCreated_date(),leave.getFrom_date(),leave.getTo_date(),leave.getReason(),leave.getApproval_status(),leave.getApprover_id(),leave.getApproval_date());
	 return jdbcTemplate.update(UPDATE_QUERY , expense.getStatus(),expense.getApprover_id(),new Date(),Integer.parseInt(expense.getExpense_id()));
	}
	catch(Exception e)
	{
		System.out.println("Exception while Updating expense"+e);
		return 0;
		
}
	
}

public int updateleave(Leave leave)
{
	try{
		
	 String UPDATE_QUERY  = "update leaves set approval_status=?,approver_id=?,approvercomments=?,approval_date=? where leave_id=?";
	 
	 //return jdbcTemplate.update(INSERT_QUERY, leave.getLeave_type(),leave.getEmp_no(),leave.getCreated_date(),leave.getFrom_date(),leave.getTo_date(),leave.getReason(),leave.getApproval_status(),leave.getApprover_id(),leave.getApproval_date());
	 return jdbcTemplate.update(UPDATE_QUERY , leave.getApproval_status(),leave.getApprover_id(),leave.getApprovercomments(),new Date(),Integer.parseInt(leave.getLeave_id()));
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
	String selectquery="select lea.* from leaves lea,dept_emp demp where lea.emp_no=demp.emp_no and demp.dept_no=? and lea.approval_status=? ";
	
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
		
		  String countsql = "select count(*) from  dept_manager  where emp_no=? and to_date=?";
		  String to_date="9999-01-01";
		  String dep_no="";
		  String depnamesql = "select dept_no from  dept_manager  where emp_no=? and to_date=?";
	      int count=jdbcTemplate.queryForObject(countsql,new Object[] {empno,to_date},Integer.class);
	      
	      if(count>0)
	      {
	    	  dep_no=jdbcTemplate.queryForObject(depnamesql,new Object[] {empno,to_date},String.class);
	    	  
	      
		  
		  leavedetails=jdbcTemplate.query(
		    			selectquery,
		    			  new Object[] { dep_no, status }, 
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


public  List <Leave>getAllLeaves(String empno)
{

	DataSource dataSource;
	Connection connection=null;
	List<Leave> leavedetails = new ArrayList<>();
	//String selectquery="select top 1 SHIP_TO_COUNTRY from  LEGAL_DB.LEGAL_DFI_BR.BV_CMPL_PLT_SERIAL_NUMBER  where LOWER_BP_SERIAL_NUMBER=? and CARTON_ID=? and LOWER_ITEM_SERIAL_NUMBER=?";
	String selectquery="select * from leaves  where emp_no=?";
	
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
				  
		  leavedetails=jdbcTemplate.query(
		    			selectquery,
		    			  new Object[] { empno }, 
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
public  List <Expense>getpendingExpense(String empno, String status)
{
	DataSource dataSource;
	Connection connection=null;
	List<Expense> expensedetails = new ArrayList<>();
	//String selectquery="select top 1 SHIP_TO_COUNTRY from  LEGAL_DB.LEGAL_DFI_BR.BV_CMPL_PLT_SERIAL_NUMBER  where LOWER_BP_SERIAL_NUMBER=? and CARTON_ID=? and LOWER_ITEM_SERIAL_NUMBER=?";
	String selectquery="select exp.* from expense exp,dept_emp demp where exp.emp_no=demp.emp_no and demp.dept_no=? and exp.status=? ";
	
	System.out.println(selectquery);

	System.out.println("empno"+empno);
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
		
		  String countsql = "select count(*) from  dept_manager  where emp_no=? and to_date=?";
		  String to_date="9999-01-01";
		  String dep_no="";
		  String depnamesql = "select dept_no from  dept_manager  where emp_no=? and to_date=?";
	      int count=jdbcTemplate.queryForObject(countsql,new Object[] {empno,to_date},Integer.class);
	      System.out.println("count"+count);
	      if(count>0)
	      {
	    	  dep_no=jdbcTemplate.queryForObject(depnamesql,new Object[] {empno,to_date},String.class);
	    	  
	      
		  
	    	  expensedetails=jdbcTemplate.query(
		    			selectquery,
		    			  new Object[] { dep_no, status }, 
		                (rs, rowNum) ->	    			
		                        new Expense(
		                                rs.getString("expense_id"),
		                                rs.getString("expense_type"),
		                                rs.getString("description"),
		                                rs.getString("amount"),
		                                rs.getString("status"),
		                                rs.getString("approver_id"),
		                                rs.getString("created_date"),
		                                rs.getString("approve_date")
		                        ));
	      }
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
		
		 return expensedetails;
	
}

public int insertPerformance(Performance performance)
{
	try{
	 String INSERT_QUERY = "insert into performance (emp_no,year,development_goals,strengths,accomplishments,responsibilities,created_date,approval_date,status,approver_comments,approver_id) values (?, ?,?, ?,?, ?,?, ?,?,?,?)";

	 //return jdbcTemplate.update(INSERT_QUERY, leave.getLeave_type(),leave.getEmp_no(),leave.getCreated_date(),leave.getFrom_date(),leave.getTo_date(),leave.getReason(),leave.getApproval_status(),leave.getApprover_id(),leave.getApproval_date());
	 return jdbcTemplate.update(INSERT_QUERY, Integer.parseInt(performance.getEmp_no()),performance.getYear(),performance.getDevelopment_goals(),performance.getStrengths(),performance.getAccomplishments(),performance.getResponsibilities(),new Date(),null,"Pending",null,null);
	}
	catch(Exception e)
	{
		System.out.println("Exception while submitting expense"+e);
		return 0;
		
}
	
}


public  List <Performance>getpendingPerformance(String empno, String status)
{
	DataSource dataSource;
	Connection connection=null;
	List<Performance> perdetails = new ArrayList<>();
	//String selectquery="select top 1 SHIP_TO_COUNTRY from  LEGAL_DB.LEGAL_DFI_BR.BV_CMPL_PLT_SERIAL_NUMBER  where LOWER_BP_SERIAL_NUMBER=? and CARTON_ID=? and LOWER_ITEM_SERIAL_NUMBER=?";
	String selectquery="select per.* from performance per,dept_emp demp where per.emp_no=demp.emp_no and demp.dept_no=? and per.status=? ";
	
	//String selectquery="select * from performance";
	System.out.println(selectquery);

	System.out.println("empno"+empno);
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
		
		  String countsql = "select count(*) from  dept_manager  where emp_no=? and to_date=?";
		  String to_date="9999-01-01";
		  String dep_no="";
		  String depnamesql = "select dept_no from  dept_manager  where emp_no=? and to_date=?";
	      int count=jdbcTemplate.queryForObject(countsql,new Object[] {empno,to_date},Integer.class);
	      System.out.println("count"+count);
	      if(count>0)
	      {
	    	  dep_no=jdbcTemplate.queryForObject(depnamesql,new Object[] {empno,to_date},String.class);
	    	  
	    	  
	    	  System.out.println("status"+status);
	      
		  
	    	  perdetails=jdbcTemplate.query(
		    			selectquery,
		    			  new Object[] { dep_no, status }, 
		                (rs, rowNum) ->	    			
		                        new Performance(
		                                rs.getString("performance_id"),
		                                rs.getString("emp_no"),
		                                rs.getString("year"),
		                                rs.getString("development_goals"),
		                                rs.getString("strengths"),
		                                rs.getString("accomplishments"),
		                                rs.getString("responsibilities"),
		                                rs.getString("created_date"),
		                                rs.getString("approval_date"),
		                                rs.getString("status"),
		                                rs.getString("approver_comments"),
		                                rs.getString("approver_id")
		                        ));
	    	  System.out.println("perdetails"+perdetails.size());
	      }
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
		
		 return perdetails;
	
}

public int updateperformance(Performance performance)
{
	try{
		
	 String UPDATE_QUERY  = "update performance set status=?,approver_id=?,approval_date=?,approver_comments=? where performance_id=?";
	 
	 //return jdbcTemplate.update(INSERT_QUERY, leave.getLeave_type(),leave.getEmp_no(),leave.getCreated_date(),leave.getFrom_date(),leave.getTo_date(),leave.getReason(),leave.getApproval_status(),leave.getApprover_id(),leave.getApproval_date());
	 return jdbcTemplate.update(UPDATE_QUERY , performance.getStatus(),performance.getApprover_id(),new Date(),performance.getApprover_comments(),Integer.parseInt(performance.getPerformance_id()));
	}
	catch(Exception e)
	{
		System.out.println("Exception while Updating performance"+e);
		return 0;
		
}
	
}
}
