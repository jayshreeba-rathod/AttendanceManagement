package com.jayshreeba.AttendanceManagement.wrapper;

public class EmployeeInfo {

	String id;
	String empName;
	String punchTime;
	String present;

	public EmployeeInfo(String id, String empName, String punchTime, String present) {
		super();
		this.id = id;
		this.empName = empName;
		this.punchTime = punchTime;
		this.present = present;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getPunchTime() {
		return punchTime;
	}

	public void setPunchTime(String punchTime) {
		this.punchTime = punchTime;
	}

	public String getPresent() {
		return present;
	}

	public void setPresent(String present) {
		this.present = present;
	}

	@Override
	public String toString() {
		return "EmployeeInfo [id=" + id + ", empName=" + empName + ", punchTime=" + punchTime + ", present=" + present
				+ "]";
	}

}
