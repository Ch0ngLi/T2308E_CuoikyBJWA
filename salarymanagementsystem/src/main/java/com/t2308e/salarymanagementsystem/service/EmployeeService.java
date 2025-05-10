package com.t2308e.salarymanagementsystem.service;

import com.t2308e.salarymanagementsystem.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.t2308e.salarymanagementsystem.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository repository;

    public List<Employee> getAll() {
        return repository.findAll();
    }

    public void save(Employee employee) throws Exception {
        Optional<Employee> existing = repository.findByName(employee.getName());
        if (existing.isPresent() && (employee.getId() == null || !existing.get().getId().equals(employee.getId()))) {
            throw new Exception("A User with name " + employee.getName() + " already exists");
        }
        repository.save(employee);
    }

    public Employee getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<Employee> search(String keyword) {
        return repository.findByNameContainingIgnoreCase(keyword);
    }
}
