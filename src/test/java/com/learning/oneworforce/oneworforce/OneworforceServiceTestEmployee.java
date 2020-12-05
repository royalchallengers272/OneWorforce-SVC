package com.learning.oneworforce.oneworforce;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.oneworforce.dao.MySQLDAO;
import com.learning.oneworforce.model.Employee;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class OneworforceServiceTestEmployee {
	@Autowired
	private MySQLDAO mysqldao;
	
	@LocalServerPort
	private int port;
	private static final Logger LOGGER = LoggerFactory.getLogger(OneworforceServiceTestEmployee.class);

	@Test
	@Rollback(false)
	public void testGetEmployee() {
		String empNo = "10002";
		List<Employee> empDetails= mysqldao.getEmployeeData(empNo);
		assertNotNull(empDetails);
		assertEquals(empNo, empDetails.get(0).getEmp_no());
		for (Employee employee : empDetails) {
			LOGGER.info(employee.toString());
		}
	}
	
	@Test
	@Rollback(false)
	public void testGetAllEmployees() {
		List<Employee> employees = mysqldao.getAllEmployeeData();
		assertNotNull(employees);
		LOGGER.info("total size is " + employees.size());
		for (Employee employee : employees) {
			LOGGER.info(employee.toString());
		}
		
	}
	
	@Test
	@Rollback(false)
	public void testUpdateEmployee() throws JsonParseException, JsonMappingException, IOException {
		File file = new File(
		        this.getClass().getClassLoader().getResource("testEmployee.json").getFile()
		    ); 
		ObjectMapper mapper = new ObjectMapper();
		Employee employee = mapper.readValue(file, Employee.class);
		int returnCode = mysqldao.updateEmployee(employee);
		assertEquals(1, returnCode);
	}
	
	@Test
	@Rollback(false)
	public void testGetManagerEmployeeData() {
		String empNo = "10002";
		List<Employee> empMgrDetails= mysqldao.getmanagerEmployeeData(empNo);
		assertNotNull(empMgrDetails);
	}
}
