package com.devng.chdb_crud.repository;

import com.devng.chdb_crud.model.Employee;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepository {
    private final JdbcTemplate jdbcTemplate;

    public EmployeeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Employee> findAll() {
        return jdbcTemplate.query("SELECT * FROM employee", (rs, rowNum) ->
                new Employee(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("dob")
                ));
    }

    public void save(Employee employee) {
        jdbcTemplate.update("INSERT INTO employee (id, name, dob) VALUES (?, ?, ?)",
                employee.getId(), employee.getName(), employee.getDob());
    }

    public void update(Employee employee) {
        jdbcTemplate.update("ALTER TABLE employee UPDATE name = ?, dob = ? WHERE id = ?",
                employee.getName(), employee.getDob(), employee.getId());
    }

    public void delete(Long id) {
        jdbcTemplate.update("ALTER TABLE employee DELETE WHERE id = ?", id);
    }
}
