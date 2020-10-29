package com.example.application.backend.dto;

public class CompanyDTO {

    private Integer id;
    private String name;

    private String industryName;
    private String notes;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIndustryName() {
        return industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
    }
    public String getNotes() {
        return notes;
    }
}
