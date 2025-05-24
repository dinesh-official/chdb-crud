package com.devng.chdb_crud.model;

public class Employee {
    private Long id;
    private String name;
    private String dob;

    public Employee() {}

    public Employee(Long id, String name, String dob) {
        this.id = id;
        this.name = name;
        this.dob = dob;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDob() { return dob; }
    public void setDob(String dob) { this.dob = dob; }
}
