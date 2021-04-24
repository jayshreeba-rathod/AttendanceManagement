package com.jayshreeba.AttendanceManagement.wrapper;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "empname")
	@NotNull(message = "Employee name cannot be empty")
	private String empName;

	@Column(name = "password")
	@Size(min = 5, max = 8, message = "Password should be between 5-8 characters")
	private String password;

	@Column(name = "position")
	private String position;

	@OneToMany(mappedBy = "employee")
	private List<Attendance> attendance;

	public Employee() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public List<Attendance> getAttendance() {
		return attendance;
	}

	public void setAttendance(List<Attendance> attendance) {
		this.attendance = attendance;
	}

	@Override
	public String toString() {
		return "Employee [empName=" + empName + ", password=" + password + ", position=" + position + "]";
	}

}
