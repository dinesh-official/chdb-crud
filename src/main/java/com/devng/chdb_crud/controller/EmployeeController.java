package com.devng.chdb_crud.controller;

import com.devng.chdb_crud.model.Employee;
import com.devng.chdb_crud.repository.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeRepository repository;

    public EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Employee> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public void create(@RequestBody Employee employee) {
        repository.save(employee);
    }

    @PutMapping
    public void update(@RequestBody Employee employee) {
        repository.update(employee);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.delete(id);
    }
}
