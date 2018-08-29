package com.andrey.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andrey.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Employee findByFirstName(String firstName);

	Employee findByEmployeeId(int employeeId);

	List<Employee> findByIsOnLeave(boolean isOnLeave);

}
