package com.jayshreeba.AttendanceManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jayshreeba.AttendanceManagement.repository.EmployeeRepository;
import com.jayshreeba.AttendanceManagement.wrapper.Employee;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository repository;

	public List<Employee> getAllEmployees() {
		return repository.findAll();
	}

	public Employee getEmployeeByEmpName(String empName) {
		return repository.findByEmpName(empName);
	}

	public Employee saveEmployee(Employee employee) {
		return repository.save(employee);
	}

	public Employee getEmployeeByEmpNameAndPassword(String empName, String password) {
		return repository.findByEmpNameAndPassword(empName, password);
	}

	public List<Object[]> findDailyEmployeeReport(String startDate, String endDate) {
		return repository.findDailyEmployeeReport(startDate, endDate);
	}
}
