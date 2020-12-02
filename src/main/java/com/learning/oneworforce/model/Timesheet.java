package com.learning.oneworforce.model;

public class Timesheet {
private String timesheet_id;
private String emp_no;
private String timesheet_date;
private String timesheet_hours;
private String tasks;
private String created_date;
private String approver_id;
private String approval_date;
private String approver_comments;
private String status;
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getTimesheet_id() {
	return timesheet_id;
}
public void setTimesheet_id(String timesheet_id) {
	this.timesheet_id = timesheet_id;
}
public String getEmp_no() {
	return emp_no;
}
public void setEmp_no(String emp_no) {
	this.emp_no = emp_no;
}
public String getTimesheet_date() {
	return timesheet_date;
}
public void setTimesheet_date(String timesheet_date) {
	this.timesheet_date = timesheet_date;
}
public String getTimesheet_hours() {
	return timesheet_hours;
}
public void setTimesheet_hours(String timesheet_hours) {
	this.timesheet_hours = timesheet_hours;
}
public String getTasks() {
	return tasks;
}
public void setTasks(String tasks) {
	this.tasks = tasks;
}
public String getCreated_date() {
	return created_date;
}
public void setCreated_date(String created_date) {
	this.created_date = created_date;
}
public String getApprover_id() {
	return approver_id;
}
public void setApprover_id(String approver_id) {
	this.approver_id = approver_id;
}
public String getApproval_date() {
	return approval_date;
}
public void setApproval_date(String approval_date) {
	this.approval_date = approval_date;
}
public String getApprover_comments() {
	return approver_comments;
}
public void setApprover_comments(String approver_comments) {
	this.approver_comments = approver_comments;
}

public Timesheet()
{
	
}


public Timesheet(String timesheet_id,String emp_no,String timesheet_date,String timesheet_hours,String tasks,String created_date,String approver_id,String approval_date,String approver_comments,String status)
{
	this.timesheet_id=timesheet_id;
	this.emp_no=emp_no;
	this.timesheet_date=timesheet_date;
	this.timesheet_hours=timesheet_hours;
	this.tasks=tasks;
	this.created_date=created_date;
	this.approver_id=approver_id;
	this.approval_date=approval_date;
	this.approver_comments=approver_comments;
	this.status=status;
	}

}
