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
import com.springdatajpa.manytoone.repository.DepartmentRepository;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@PostMapping
	public ResponseEntity<Department> createDepartment(@RequestBody Department department)
	{
		Department savedDepartment = departmentRepository.save(department);
		return ResponseEntity.ok(savedDepartment);
	}
	
	 @PostMapping("/saveAll")
	 public ResponseEntity<List<Department>> saveAllDepartments(@RequestBody List<Department> departments) {
	     List<Department> savedDepartments = departmentRepository.saveAll(departments);
	     return ResponseEntity.ok(savedDepartments);
	 }
	
	@GetMapping
	public ResponseEntity<List<Department>> getAllDepartments()
	{
		List<Department> departments = departmentRepository.findAll();
		return ResponseEntity.ok(departments);
	}
	
	 @PutMapping("/{id}")
	    public ResponseEntity<Department> updateDepartment(@PathVariable Long id, @RequestBody Department updatedDepartment) {
	        Department department = departmentRepository.findById(id)
	                .orElseThrow(() -> new IllegalArgumentException("Invalid department id"));

	        department.setName(updatedDepartment.getName());

	        Department updatedDepartment1 = departmentRepository.save(department);
	        return ResponseEntity.ok(updatedDepartment1);
	    }
	 
	 @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
	        Department department = departmentRepository.findById(id)
	                .orElseThrow(() -> new IllegalArgumentException("Invalid department id"));

	        departmentRepository.delete(department);
	        return ResponseEntity.noContent().build();
	    }
	 
	
}
