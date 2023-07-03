package com.springdatajpa.manytoone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springdatajpa.manytoone.entity.Department;
import com.springdatajpa.manytoone.entity.Employee;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long>{

}
