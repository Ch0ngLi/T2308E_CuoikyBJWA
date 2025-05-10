package com.t2308e.salarymanagementsystem.repository;

import com.t2308e.salarymanagementsystem.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByName(String name);
    List<Employee> findByNameContainingIgnoreCase(String name);
}