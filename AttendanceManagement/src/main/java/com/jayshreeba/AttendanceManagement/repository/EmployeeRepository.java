package com.jayshreeba.AttendanceManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jayshreeba.AttendanceManagement.wrapper.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	Employee findByEmpName(String empName);

	@Query("SELECT e FROM Employee e WHERE e.empName = ?1 and e.password = ?2")
	Employee findByEmpNameAndPassword(String empName, String password);

	@Query(nativeQuery = true, value = " select e.id,e.empName, "
			+ "case WHEN a.punchtime is null THEN '' ELSE date(a.punchtime) END AS 'punchDate', "
			+ "case WHEN a.punchtime is null THEN '' ELSE time(a.punchtime) END AS 'punchTime', "
			+ "case WHEN a.punchtime is null THEN 'A' ELSE 'P' END AS 'present' FROM  employee e  "
			+ "left join ( select * from attendance where date(punchtime) between :startDate and :endDate ) a "
			+ "ON e.id = a.empid WHERE e.position = 'Employee'  ")
	List<Object[]> findDailyEmployeeReport(@Param("startDate") String startDate, @Param("endDate") String endDate);

	// @Query(nativeQuery = true, value = "SELECT * FROM Employee e WHERE e.empName
	// = :empName and e.password = :password")
	// Employee findByEmpNameAndPassword(@Param("empName") String empName,
	// @Param("password") String password);

}
