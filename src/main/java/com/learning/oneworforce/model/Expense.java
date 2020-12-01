package com.learning.oneworforce.model;

public class Expense {

	private String expense_id;
	private String emp_no;
	public String getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}

	private String expense_type;
	private String description;
	private String amount;
	private String status;
	private String approver_id;
	private String created_date;
	private String approve_date;
	public String getApprover_id() {
		return approver_id;
	}
	public void setApprover_id(String approver_id) {
		this.approver_id = approver_id;
	}
	public String getCreated_date() {
		return created_date;
	}
	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}
	public String getApprove_date() {
		return approve_date;
	}
	public void setApprove_date(String approve_date) {
		this.approve_date = approve_date;
	}
	public String getExpense_id() {
		return expense_id;
	}
	public void setExpense_id(String expense_id) {
		this.expense_id = expense_id;
	}
	public String getExpense_type() {
		return expense_type;
	}
	public void setExpense_type(String expense_type) {
		this.expense_type = expense_type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Expense()
	{
		
	}
	
	public Expense(String expense_id,String expense_type,String description,String amount,String status,String approver_id,String created_date,String approve_date)
	{
		this.expense_id=expense_id;
		this.expense_type=expense_type;
		this.description=description;
		this.amount=amount;
		this.status=status;
		this.approver_id=approver_id;
		this.created_date=created_date;
		this.approve_date=approve_date;
	}
	
	}
