package com.learning.oneworforce.model;

import java.util.Date;

public class Leave {
	
private String leave_id;
private String leave_type;
private int emp_no;
private String from_date;
private String to_date;
private String approval_status;
private String reason;
private int approver_id;
private String created_date;
private String approvercomments;
private String empname;
public String getEmpname() {
	return empname;
}
public void setEmpname(String empname) {
	this.empname = empname;
}
public String getApprovercomments() {
	return approvercomments;
}
public void setApprovercomments(String approvercomments) {
	this.approvercomments = approvercomments;
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

private String approval_date;
public String getLeave_id() {
	return leave_id;
}
public void setLeave_id(String leave_id) {
	this.leave_id = leave_id;
}
public String getLeave_type() {
	return leave_type;
}
public void setLeave_type(String leave_type) {
	this.leave_type = leave_type;
}
public int getEmp_no() {
	return emp_no;
}
public void setEmp_no(int emp_no) {
	this.emp_no = emp_no;
}
public String getFrom_date() {
	return from_date;
}
public void setFrom_date(String from_date) {
	this.from_date = from_date;
}
public String getTo_date() {
	return to_date;
}
public void setTo_date(String to_date) {
	this.to_date = to_date;
}
public String getApproval_status() {
	return approval_status;
}
public void setApproval_status(String approval_status) {
	this.approval_status = approval_status;
}
public String getReason() {
	return reason;
}
public void setReason(String reason) {
	this.reason = reason;
}
public int getApprover_id() {
	return approver_id;
}
public void setApprover_id(int approver_id) {
	this.approver_id = approver_id;
}

public Leave(String leave_id,int emp_no,String created_date,String leave_type,String from_date,String to_date,String  approval_status,String reason,String approval_date,int approver_id,String approvercomments,String empname)
{
	this.leave_id=leave_id;
	this.emp_no=emp_no;
	this.leave_type=leave_type;
	this.from_date=from_date;
	this.to_date=to_date;
	this.approval_status=approval_status;
	this.reason=reason;
	this.approver_id=approver_id;
	this.created_date=created_date;
	this.approval_date=approval_date;
	this.approvercomments=approvercomments;
	this.empname=empname;
}

public Leave()
{
	
}
}
