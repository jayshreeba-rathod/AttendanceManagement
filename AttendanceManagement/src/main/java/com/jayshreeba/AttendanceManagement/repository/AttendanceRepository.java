package com.jayshreeba.AttendanceManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jayshreeba.AttendanceManagement.wrapper.Attendance;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {

	// @Query("SELECT a FROM Attendance a WHERE a.employee.id = ?1 and
	// date(a.punchTime) = ?2")
	@Query(nativeQuery = true, value = " SELECT * FROM Attendance a WHERE a.empid = :empId and date(a.punchTime) = :punchDate")
	List<Attendance> findByEmpIdPunchDate(@Param("empId") Integer empId, @Param("punchDate") String punchDate);

}
