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
import com.learning.oneworforce.model.Performance;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class OneworforceServiceTestPerformance {
	@Autowired
	private MySQLDAO mysqldao;

	@LocalServerPort
	private int port;

	@Test
	@Rollback(false)
	public void testGetPendingPerformance() {
		String empNo = "10001"; 
		String status = "Pending";
		List<Performance> perfDetails = mysqldao.getpendingPerformance(empNo, status);
		assertNotNull(perfDetails);
		for (Performance perf : perfDetails) {
			assertNotNull(perf.getEmp_no());
			assertNotNull(perf.getPerformance_id());
			assertEquals("Pending", perf.getStatus());
			assertNotNull(perf.getAccomplishments());
			assertNotNull(perf.getResponsibilities());
		}
	}

	@Test
	@Rollback(false)
	public void testInsertPerformance() throws JsonParseException, JsonMappingException, IOException {
		File file = new File(
		        this.getClass().getClassLoader().getResource("testInsertPerformance.json").getFile()
		    ); 
		ObjectMapper mapper = new ObjectMapper();
		Performance perf = mapper.readValue(file, Performance.class);
		int returnCode = mysqldao.insertPerformance(perf);
		assertEquals(1, returnCode);
	}
	
	@Test
	@Rollback(false)
	public void testUpdateTimesheet() throws JsonParseException, JsonMappingException, IOException {
		File file = new File(
		        this.getClass().getClassLoader().getResource("testUpdatePerformance.json").getFile()
		        );
		ObjectMapper mapper = new ObjectMapper();
		Performance perf = mapper.readValue(file, Performance.class);
		int returnCode = mysqldao.updateperformance(perf);
		assertEquals(1, returnCode);
		// Performing a getpendingPerformance to check if update is working
		String empNo = "10001"; 
		String status = "Pending";
		List<Performance> perfDetails = mysqldao.getpendingPerformance(empNo, status);
		assertNotNull(perfDetails);
		for (Performance testPerf : perfDetails) {
			assertNotNull(empNo, testPerf.getEmp_no());
			assertNotNull(testPerf.getPerformance_id());
			assertEquals("Pending", testPerf.getStatus());
			assertNotNull(testPerf.getAccomplishments());
			assertNotNull(testPerf.getResponsibilities());
		}

	}
}