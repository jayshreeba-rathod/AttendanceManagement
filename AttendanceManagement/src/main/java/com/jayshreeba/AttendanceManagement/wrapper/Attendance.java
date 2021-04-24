package com.jayshreeba.AttendanceManagement.wrapper;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "attendance")
public class Attendance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	/*
	 * @Column(name = "empid") private Integer empId;
	 */
	@Column(name = "punchtime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date punchTime;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "empid", referencedColumnName = "id")
	private Employee employee;

	public Attendance() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getPunchTime() {
		return punchTime;
	}

	public void setPunchTime(Date punchTime) {
		this.punchTime = punchTime;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "Attendance [id=" + id + ", punchTime=" + punchTime + ", employee=" + employee + "]";
	}

}
