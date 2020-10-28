package com.example.application.backend.dto;

public class CompanyDTO {

    private final Integer id;
    private final String name;
    private final String industryName;
    private final String notes;

    public CompanyDTO(Integer id, String name, String industryName, String notes) {
        this.id = id;
        this.name = name;
        this.industryName = industryName;
        this.notes = notes;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIndustryName() {
        return industryName;
    }

    public String getNotes() {
        return notes;
    }
}
