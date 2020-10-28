package com.example.application.backend.dto;

public class EmployeeDTO {

    private final String firstName;
    private final String lastName;

    public EmployeeDTO(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
