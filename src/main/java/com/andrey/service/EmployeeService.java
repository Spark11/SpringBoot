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

	public Employee findById(Long id) {
		try {
			return employeeRepository.findById(id).orElseThrow(() -> new Exception("Employee not found."));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
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

	public Employee addEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	public Employee replaceEmployee(Employee newEmployee, Long id) {
		return employeeRepository.findById(id).map(employee -> {
			employee.setEmployeeId(newEmployee.getEmployeeId());
			employee.setFirstName(newEmployee.getFirstName());
			employee.setOnLeave(newEmployee.isOnLeave());
			return employeeRepository.save(employee);
		}).orElseGet(() -> {
			newEmployee.setId(id);
			return employeeRepository.save(newEmployee);
		});
	}

	public void deleteEmployee(Long id) {
		employeeRepository.deleteById(id);
	}
}
