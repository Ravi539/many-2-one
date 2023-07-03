package com.springdatajpa.manytoone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springdatajpa.manytoone.entity.Department;
import com.springdatajpa.manytoone.entity.Employee;
import com.springdatajpa.manytoone.repository.DepartmentRepository;
import com.springdatajpa.manytoone.repository.EmployeeRepository;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private DepartmentRepository departmentRepository;

	 
	@PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(savedEmployee);
    } 
	
	 
	 @GetMapping
		public ResponseEntity<List<Employee>> getAllEmployees()
		{
			List<Employee> employees = employeeRepository.findAll();
			return ResponseEntity.ok(employees);
		}
	 
	 @PostMapping("/saveAll")
	    public ResponseEntity<List<Employee>> saveAllEmployees(@RequestBody List<Employee> employees) {
	        List<Employee> savedEmployees = employeeRepository.saveAll(employees);
	        return ResponseEntity.ok(savedEmployees);
	    }
	 
	 @PutMapping("/{id}")
	    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
	        Employee employee = employeeRepository.findById(id)
	                .orElseThrow(() -> new IllegalArgumentException("Invalid employee id"));

	        employee.setName(updatedEmployee.getName());
	        employee.setDepartment(updatedEmployee.getDepartment());

	        Employee updatedEmployee1 = employeeRepository.save(employee);
	        return ResponseEntity.ok(updatedEmployee1);
	    }
	 
	 	@DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
	        employeeRepository.deleteById(id);
	        return ResponseEntity.noContent().build();
	    }

	   

}
