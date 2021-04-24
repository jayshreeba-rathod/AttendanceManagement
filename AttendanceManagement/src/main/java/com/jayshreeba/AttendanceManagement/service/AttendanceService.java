package com.jayshreeba.AttendanceManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jayshreeba.AttendanceManagement.repository.AttendanceRepository;
import com.jayshreeba.AttendanceManagement.wrapper.Attendance;

@Service
public class AttendanceService {

	@Autowired
	AttendanceRepository repository;

	public List<Attendance> getAllDetails() {
		return repository.findAll();
	}

	public Attendance savePunchDetail(Attendance attendance) {
		return repository.save(attendance);
	}

	public List<Attendance> getAttendancePunchByEmpId(Integer empId,String punchDate) {
		return repository.findByEmpIdPunchDate(empId,punchDate);
	}

}
