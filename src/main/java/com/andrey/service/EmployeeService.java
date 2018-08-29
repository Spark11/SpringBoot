package com.andrey.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andrey.dao.EmployeeRepository;
import com.andrey.entity.Employee;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public List<Employee> findAllEmployees() {
		return employeeRepository.findAll();
	}

	public Employee findByFirstName(String firstName) {
		return employeeRepository.findByFirstName(firstName);
	}
	
	public Employee findByEmployeeId(int employeeId) {
		return employeeRepository.findByEmployeeId(employeeId);
	}
	
	public List<Employee> findByIsOnLeave(boolean isOnLeave) {
		return employeeRepository.findByIsOnLeave(isOnLeave);
	}
	
	public void addEmployee(Employee employee) {
		employeeRepository.save(employee);
	}
}
