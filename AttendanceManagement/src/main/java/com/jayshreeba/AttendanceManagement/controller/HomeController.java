package com.jayshreeba.AttendanceManagement.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jayshreeba.AttendanceManagement.service.AttendanceService;
import com.jayshreeba.AttendanceManagement.service.EmployeeService;
import com.jayshreeba.AttendanceManagement.wrapper.Attendance;
import com.jayshreeba.AttendanceManagement.wrapper.Employee;

@Controller
public class HomeController {

	@Autowired
	EmployeeService employeeService;

	@Autowired
	AttendanceService attendanceService;

	@RequestMapping(value = "/")
	public String indexPage(HttpServletRequest request, Model model) {
		if (setModelAttributes(request, model))
			return "redirect:/punch";

		model.addAttribute("employee", new Employee());
		return "index";
	}

	@RequestMapping(value = "/logout", method = { RequestMethod.GET })
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		sessionInvalidation(request);
		return "redirect:/login";
	}

	private void sessionInvalidation(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session != null && session.getAttribute("empName") != null) {
			session.setAttribute("empName", null);
			session.setAttribute("empPosition", null);
			session.invalidate();
		}
	}

	@RequestMapping(value = "/signUpSubmit", method = RequestMethod.POST)
	public String signUpSubmit(@Valid @ModelAttribute Employee employee, BindingResult result) {
		Employee empExists = employeeService.getEmployeeByEmpName(employee.getEmpName());
		if (empExists != null) {
			result.rejectValue("empName", "error.empName", "Employee name already exists.");
		}
		if (result.hasErrors()) {
			return "index";
		}
		employeeService.saveEmployee(employee);
		return "redirect:/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(HttpServletRequest request, Model model) {
		if (setModelAttributes(request, model))
			return "redirect:/punch";
		model.addAttribute("employees", new Employee());
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginSubmit(HttpServletRequest request, @Valid @ModelAttribute Employee employees,
			BindingResult result) {
		HttpSession session = request.getSession();
		Employee empExists = employeeService.getEmployeeByEmpNameAndPassword(employees.getEmpName(),
				employees.getPassword());
		if (empExists == null) {
			result.rejectValue("empName", "error.empName", "Invalid Employee name or password.");
		}

		if (result.hasErrors()) {
			return "login";
		}
		session.setAttribute("empName", empExists.getEmpName());
		session.setAttribute("empPosition", empExists.getPosition());
		return "redirect:/punch";
	}

	@RequestMapping(value = "/punch", method = RequestMethod.GET)
	public String punch(HttpServletRequest request, Model model) {
		setModelAttributes(request, model);
		return "punch";
	}

	@RequestMapping(value = "/punchTime", method = RequestMethod.POST)
	public @ResponseBody String punchTime(HttpServletRequest request, Model model) {
		setModelAttributes(request, model);
		String empName = model.getAttribute("empName").toString();
		Attendance attendance = new Attendance();

		Employee employee = employeeService.getEmployeeByEmpName(empName);
		String currentDate = getCurrentDate();
		List<Attendance> attendanceExists = attendanceService.getAttendancePunchByEmpId(employee.getId(), currentDate);
		String message = "";
		if (attendanceExists != null && !attendanceExists.isEmpty()) {
			message += "You have already punched in for the day.";
		} else {
			attendance.setEmployee(employee);
			attendance.setPunchTime(new Date());

			employee.setAttendance(Arrays.asList(attendance));
			attendanceService.savePunchDetail(attendance);
			message += "You have successfully punched in.";
		}
		return message;
	}

	@RequestMapping(value = "/reports", method = RequestMethod.GET)
	public String reports(HttpServletRequest request, Model model) {
		if (!setModelAttributes(request, model))
			return "redirect:/login";
		return "reports";
	}

	@RequestMapping(value = "/getEmployeeInfo", method = RequestMethod.POST)
	public @ResponseBody String getEmployeeInfo(HttpServletRequest request, Model model,
			@RequestParam(value = "dailyReportDate", required = false) String dailyReportDate,
			@RequestParam(value = "start", required = false) String start,
			@RequestParam(value = "end", required = false) String end) {

		List<Object[]> employees = null;
		String currentDate = getCurrentDate();
		if (dailyReportDate == null || dailyReportDate == "") {
			if ((start == null || start == "") && (end == null || end == "")) {
				start = currentDate;
				end = currentDate;
			}
		} else {
			start = dailyReportDate;
			end = dailyReportDate;
		}
		
		employees = employeeService.findDailyEmployeeReport(start, end);
		JsonArray jsonArray = new JsonArray();
		JsonObject jsonResponse = new JsonObject();

		for (Object[] objects : employees) {
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("0", checkNull(objects[1]));
			jsonObject.addProperty("1", checkNull(objects[2]));
			jsonObject.addProperty("2", checkNull(objects[3]));
			String presentOrAbsent = checkNull(objects[4]);

			jsonObject.addProperty("3",
					presentOrAbsent.charAt(0) == 'P' ? "<span class='empPresent' >" + checkNull(objects[4]) + "</span>"
							: "<span class='empAbsent' >" + checkNull(objects[4]) + "</span>");
			jsonArray.add(jsonObject);
		}
		jsonResponse.add("data", jsonArray);
		return new Gson().toJson(jsonResponse);
	}

	private String getCurrentDate() {
		LocalDateTime localDateTime = LocalDateTime.now();
		String date = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(localDateTime);
		return date;
	}

	public String checkNull(Object value) {
		return value != null ? value.toString() : "";
	}

	private boolean setModelAttributes(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		if (session != null) {
			String empName = (String) session.getAttribute("empName");
			if (empName != null) {
				model.addAttribute("empName", empName);
				model.addAttribute("empPosition", session.getAttribute("empPosition"));
				return true;
			}
			return false;
		}
		return false;
	}
	
	@RequestMapping(value = "/datePicker", method = RequestMethod.GET)
	public String datePicker(HttpServletRequest request, Model model) {
		return "datePicker";
	}
}
