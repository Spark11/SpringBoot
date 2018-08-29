package com.andrey.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.andrey.entity.Employee;
import com.andrey.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	private EmployeeService employeeService;
	private final EmployeeResourceAssembler assembler;

	public EmployeeController(EmployeeService employeeService, EmployeeResourceAssembler assembler) {
		super();
		this.employeeService = employeeService;
		this.assembler = assembler;
	}

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, "application/hal+json" })
	public Resources<Resource<Employee>> findAllEmployees() {

		List<Resource<Employee>> employees = employeeService.findAllEmployees().stream().map(assembler::toResource)
				.collect(Collectors.toList());

		return new Resources<>(employees, linkTo(methodOn(EmployeeController.class).findAllEmployees()).withSelfRel());
	}

	@PostMapping
	public Employee addEmployee(@RequestBody Employee employee) {
		return employeeService.addEmployee(employee);
	}

	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, "application/hal+json" })
	public Resource<Employee> findEmployeeById(@PathVariable Long id) {
		return assembler.toResource(employeeService.findById(id));
	}

	@PutMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, "application/hal+json" })
	public Employee replaceEmployee(@RequestBody Employee employee, @PathVariable Long id) {
		return employeeService.replaceEmployee(employee, id);
	}

	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable Long id) {
		employeeService.deleteEmployee(id);
	}

	@GetMapping(value = "/{id}/return", produces = { MediaType.APPLICATION_JSON_VALUE, "application/hal+json" })
	public ResponseEntity<ResourceSupport> returnEmployeeFromLeave(@PathVariable Long id) {
		return changeEmployeeOnLeave(id, false);
	}
	
	@GetMapping(value = "/{id}/leave", produces = { MediaType.APPLICATION_JSON_VALUE, "application/hal+json" })
	public ResponseEntity<ResourceSupport> letEmployeeOnLeave(@PathVariable Long id) {
		return changeEmployeeOnLeave(id, true);
	}

	private ResponseEntity<ResourceSupport> changeEmployeeOnLeave(Long id, boolean newIsOnLeave) {
		Employee employee = employeeService.findById(id);
		if (employee.isOnLeave() != newIsOnLeave) {
			employee.setOnLeave(newIsOnLeave);
			return ResponseEntity.ok(assembler.toResource(employeeService.addEmployee(employee)));
		}
		return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(new VndErrors.VndError("Method not allowed",
				"Employee is " + (employee.isOnLeave() ? "already on leave." : "not on leave yet.")));
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

	// @RequestMapping(value = "/add", method = RequestMethod.GET)
	// public @ResponseBody String addEmployee(@RequestParam int employeeId,
	// @RequestParam String firstName,
	// @RequestParam boolean isOnLeave) {
	// Employee employee = new Employee(employeeId, firstName, isOnLeave);
	// employeeService.addEmployee(employee);
	// return "saved";
	// }

}