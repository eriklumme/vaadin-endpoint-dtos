package com.example.application.backend.entity;

import javax.persistence.Entity;
import javax.persistence.Lob;

/**
 * Represents an industry that a company may belong to,
 * such as finance or retail.
 *
 * It also includes the last employment report on the industry by the
 * Association of Reporters on Sustainable Employment.
 */
@Entity
public class Industry extends AbstractEntity {

    private String name;

    @Lob
    private byte[] employmentReport;

    public Industry() {
    }

    public Industry(String name, byte[] employmentReport) {
        this.name = name;
        this.employmentReport = employmentReport;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getEmploymentReport() {
        return employmentReport;
    }

    public void setEmploymentReport(byte[] employmentReport) {
        this.employmentReport = employmentReport;
    }
}
