package com.learning.oneworforce.dao;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import com.learning.oneworforce.model.Employee;

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
	
}
