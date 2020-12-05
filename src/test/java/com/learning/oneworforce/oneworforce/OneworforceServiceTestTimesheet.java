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
import com.learning.oneworforce.model.Timesheet;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class OneworforceServiceTestTimesheet {
	@Autowired
	private MySQLDAO mysqldao;

	@LocalServerPort
	private int port;

	@Test
	@Rollback(false)
	public void testGetPendingTimesheet() {
		String empNo = "10001";
		String status = "Pending";
		List<Timesheet> timeSheetDetails = mysqldao.getpendingTimesheet(empNo, status);
		assertNotNull(timeSheetDetails);
		for (Timesheet time : timeSheetDetails) {
			assertNotNull(time.getTimesheet_id());
			assertNotNull(time.getEmp_no());
			assertNotNull(time.getTimesheet_date());
			assertNotNull(time.getTimesheet_hours());
		}
	}

	@Test
	@Rollback(false)
	public void testInsertTimesheet() throws JsonParseException, JsonMappingException, IOException {
		File file = new File(
		        this.getClass().getClassLoader().getResource("testInsertTimesheet.json").getFile()
		    ); 
		ObjectMapper mapper = new ObjectMapper();
		Timesheet time = mapper.readValue(file, Timesheet.class);
		int returnCode = mysqldao.insertTimesheet(time);
		assertEquals(1, returnCode);
	}
	
	@Test
	@Rollback(false)
	public void testUpdateTimesheet() throws JsonParseException, JsonMappingException, IOException {
		File file = new File(
		        this.getClass().getClassLoader().getResource("testUpdateTimesheet.json").getFile()
		        );
		ObjectMapper mapper = new ObjectMapper();
		Timesheet time = mapper.readValue(file, Timesheet.class);
		int returnCode = mysqldao.updateTimesheet(time);
		assertEquals(1, returnCode);
		// Performing a getpendingTimesheet to check if update is working
		String empNo = "10001"; 
		String status = "Pending";
		List<Timesheet> timeSheetDetails = mysqldao.getpendingTimesheet(empNo, status);
		assertNotNull(timeSheetDetails);
		for (Timesheet timesheet : timeSheetDetails) {
			assertNotNull(timesheet.getTimesheet_id());
			assertNotNull(timesheet.getEmp_no());
			assertNotNull(timesheet.getTimesheet_date());
			assertNotNull(timesheet.getTimesheet_hours());
		}
	}
}