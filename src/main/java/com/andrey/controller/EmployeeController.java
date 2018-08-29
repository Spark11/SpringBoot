package com.andrey.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.andrey.entity.Employee;
import com.andrey.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(method = RequestMethod.GET)
	public List<Employee> findAllEmployees() {
		return employeeService.findAllEmployees();
	}

	@RequestMapping(value = "/name/{firstName}", method = RequestMethod.GET)
	public Employee findEmployeeByFirstName(@PathVariable("firstName") String firstName) {
		return employeeService.findByFirstName(firstName);
	}

	@RequestMapping(value = "/emp_id/{employeeId}", method = RequestMethod.GET)
	public Employee findEmployeeByEmployeeId(@PathVariable("employeeId") int employeeId) {
		return employeeService.findByEmployeeId(employeeId);
	}

	@RequestMapping(value = "/on_leave/{isOnLeave}", method = RequestMethod.GET)
	public List<Employee> findByOnLeave(@PathVariable boolean isOnLeave) {
		return employeeService.findByIsOnLeave(isOnLeave);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public @ResponseBody String addEmployee(@RequestParam int employeeId, @RequestParam String firstName,
			@RequestParam boolean isOnLeave) {
		Employee employee = new Employee(employeeId, firstName, isOnLeave);
		employeeService.addEmployee(employee);
		return "saved";
	}

}