package com.andrey.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private int employeeId;
	private String firstName;
	private boolean isOnLeave;

	public Employee() {
		super();
	}

	public Employee(int employeeId, String firstName, boolean isOnLeave) {
		super();
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.isOnLeave = isOnLeave;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public boolean isOnLeave() {
		return isOnLeave;
	}

	public void setOnLeave(boolean isOnLeave) {
		this.isOnLeave = isOnLeave;
	}

}
