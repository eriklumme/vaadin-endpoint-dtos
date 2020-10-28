package com.example.application.backend.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Company extends AbstractEntity {

    private String name;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Employee> employees;

    @Column(length = 2000)
    private String notes;

    @ManyToOne
    private Industry industry;

    public Company() {
    }

    public Company(String name, List<Employee> employees, String notes, Industry industry) {
        this.name = name;
        this.employees = employees;
        this.notes = notes;
        this.industry = industry;

        employees.forEach(employee -> employee.setCompany(this));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Industry getIndustry() {
        return industry;
    }

    public void setIndustry(Industry industry) {
        this.industry = industry;
    }
}
