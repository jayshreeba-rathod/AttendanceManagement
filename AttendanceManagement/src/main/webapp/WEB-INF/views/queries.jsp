Queries used while designing database


CREATE SCHEMA `attendance_management` ;

CREATE TABLE `attendance_management`.`employee` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `empName` VARCHAR(50) NOT NULL,
  `password` VARCHAR(8) NOT NULL,
  `position` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id`));
  
  
ALTER TABLE employee AUTO_INCREMENT = 1;

CREATE TABLE `attendance_management`.`attendance` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `empId` INT NOT NULL,
  `punchtime` DATETIME NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `foreign key`  FOREIGN KEY (`empId`)
  REFERENCES `attendance_management`.`employee` (`id`)
);

SELECT e FROM Employee e WHERE e.empName = "" and e.password = ""

select e.id,e.empName, 
case WHEN a.punchtime is null THEN '' ELSE date(a.punchtime) END AS 'punchDate',
case WHEN a.punchtime is null THEN '' ELSE time(a.punchtime) END AS 'punchTime',
case WHEN a.punchtime is null THEN 'A' ELSE 'P' END AS 'present'
FROM  employee e left join (
select * from attendance where date(punchtime) between '2021-04-22' and '2021-04-22' ) a
ON e.id = a.empid WHERE e.position = 'Employee'
   
   
SELECT * FROM Attendance a WHERE a.empid = "" and date(a.punchTime) = ""
  


