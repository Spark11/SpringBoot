package com.andrey.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import com.andrey.entity.Employee;

@Component
public class EmployeeResourceAssembler implements ResourceAssembler<Employee, Resource<Employee>> {

	@Override
	public Resource<Employee> toResource(Employee employee) {
		Resource<Employee> employeeResource = new Resource<>(employee,
				linkTo(methodOn(EmployeeController.class).findEmployeeById(employee.getId())).withSelfRel(),
				linkTo(methodOn(EmployeeController.class).findAllEmployees()).withRel("employees"));
		
		if(employee.isOnLeave()) {
			employeeResource.add(linkTo(methodOn(EmployeeController.class).returnEmployeeFromLeave(employee.getId())).withRel("return"));
		}
		else {
			employeeResource.add(linkTo(methodOn(EmployeeController.class).letEmployeeOnLeave(employee.getId())).withRel("leave"));
		}
		return employeeResource;
	}

}
