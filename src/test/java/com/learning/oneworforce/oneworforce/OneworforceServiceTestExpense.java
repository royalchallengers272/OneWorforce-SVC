package com.learning.oneworforce.oneworforce;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
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
import com.learning.oneworforce.model.Expense;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class OneworforceServiceTestExpense {
	@Autowired
	private MySQLDAO mysqldao;
	
	@LocalServerPort
	private int port;
	private static final Logger LOGGER = LoggerFactory.getLogger(OneworforceServiceTestEmployee.class);

	@Test
	@Rollback(false)
	public void testGetPendingExpense() {
		String empNo = "10001"; 
		List<Expense> expenses = mysqldao.getpendingExpense(empNo, "Pending");
		assertNotNull(expenses);
		for (Expense expense : expenses) {
			LOGGER.info(expense.toString());
		}
	}
	
	@Test
	@Rollback(false)
	public void testInsertExpense() throws JsonParseException, JsonMappingException, IOException {
		File file = new File(
		        this.getClass().getClassLoader().getResource("testExpense.json").getFile()
		    ); 
		ObjectMapper mapper = new ObjectMapper();
		Expense expense = mapper.readValue(file, Expense.class);
		int returnCode = mysqldao.insertExpense(expense);
		assertEquals(1, returnCode);
	}
	
	@Test
	@Rollback(false)
	public void testUpdateExpense() throws JsonParseException, JsonMappingException, IOException {
		File file = new File(
		        this.getClass().getClassLoader().getResource("testExpenseUpdated.json").getFile()
		    ); 
		ObjectMapper mapper = new ObjectMapper();
		Expense expense = mapper.readValue(file, Expense.class);
		int returnCode = mysqldao.updateexpense(expense);
		assertEquals(1, returnCode);
		String empNo = "10001";
		List<Expense> expenses = mysqldao.getpendingExpense(empNo, "Pending");
		assertNotNull(expenses);
		for (Expense expenseTest : expenses) {
			if (expenseTest.getEmp_no().equals(expense.getEmp_no())) {
				assertTrue(expenseTest.getExpense_id() != expense.getExpense_id());
			}
		}
	}
}
