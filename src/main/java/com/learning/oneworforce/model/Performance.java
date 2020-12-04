package com.learning.oneworforce.model;

public class Performance {

	private String performance_id;
	private String emp_no;
	private String year;
	private String development_goals;
	private String strengths;
	private String accomplishments;
	private String responsibilities;
	private String created_date;
	private String approval_date;
	private String status;
	private String approver_id;
	private String empname;
	public String getEmpname() {
		return empname;
	}
	public void setEmpname(String empname) {
		this.empname = empname;
	}
	public String getApprover_id() {
		return approver_id;
	}
	public void setApprover_id(String approver_id) {
		this.approver_id = approver_id;
	}


	private String approver_comments;
	public String getPerformance_id() {
		return performance_id;
	}
	public void setPerformance_id(String performance_id) {
		this.performance_id = performance_id;
	}
	public String getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getDevelopment_goals() {
		return development_goals;
	}
	public void setDevelopment_goals(String development_goals) {
		this.development_goals = development_goals;
	}
	public String getStrengths() {
		return strengths;
	}
	public void setStrengths(String strengths) {
		this.strengths = strengths;
	}
	public String getAccomplishments() {
		return accomplishments;
	}
	public void setAccomplishments(String accomplishments) {
		this.accomplishments = accomplishments;
	}
	public String getResponsibilities() {
		return responsibilities;
	}
	public void setResponsibilities(String responsibilities) {
		this.responsibilities = responsibilities;
	}
	public String getCreated_date() {
		return created_date;
	}
	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}
	public String getApproval_date() {
		return approval_date;
	}
	public void setApproval_date(String approval_date) {
		this.approval_date = approval_date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getApprover_comments() {
		return approver_comments;
	}
	public void setApprover_comments(String approver_comments) {
		this.approver_comments = approver_comments;
	}
	
	public Performance()
	{
		
	}

	
	public Performance(String performance_id,String emp_no,String year,String development_goals,String strengths,String accomplishments,String responsibilities,String created_date,String approval_date,String status,String approver_comments,String approver_id,String empname)
	{
		this.performance_id=performance_id;
		this.emp_no=emp_no;
		this.year=year;
		this.development_goals=development_goals;
		this.strengths=strengths;
		this.accomplishments=accomplishments;
		this.responsibilities=responsibilities;
		this.created_date=created_date;
		this.approval_date=approval_date;
		this.status=status;
		this.approver_comments=approver_comments;
		this.approver_id=approver_id;
		this.empname=empname;
	}
}
