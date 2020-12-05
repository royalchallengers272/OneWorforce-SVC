package com.learning.oneworforce.oneworforce;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
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
import com.learning.oneworforce.model.Leave;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional

public class OneworforceServiceTestLeave {

	@Autowired
	private MySQLDAO mysqldao;
	
	@LocalServerPort
	private int port;
	
	@Test
	public void testGetAllLeaves() {
		String empId = "10003";
		List<Leave> leaves = mysqldao.getAllLeaves(empId);
		for (Leave leave : leaves) {
			assertEquals(Integer.parseInt(empId), leave.getEmp_no());
			assertNotNull(leave.getLeave_id());
		}
	}
	
	@Test
	@Rollback(false)
	public void testInsertLeave() throws JsonParseException, JsonMappingException, IOException {
		File file = new File(
		        this.getClass().getClassLoader().getResource("testLeave.json").getFile()
		    ); 
		ObjectMapper mapper = new ObjectMapper();
		Leave leave = mapper.readValue(file, Leave.class);
		int returnCode = mysqldao.insertleave(leave);
		assertEquals(1, returnCode);
		List<Leave> leaves = mysqldao.getAllLeaves(String.valueOf(leave.getEmp_no()));
		for (Leave leaveTest : leaves) {
			assertEquals(leave.getEmp_no(), leaveTest.getEmp_no());
			assertNotNull(leaveTest.getLeave_id());
		}
	}
	
	@Test
	@Rollback(false)
	public void testUpdateLeave() throws JsonParseException, JsonMappingException, IOException {
		File file = new File(
		        this.getClass().getClassLoader().getResource("testUpdateLeave.json").getFile()
		    ); 
		ObjectMapper mapper = new ObjectMapper();
		Leave leave = mapper.readValue(file, Leave.class);
		int returnCode = mysqldao.updateleave(leave);
		assertEquals(1, returnCode);
	}
}
