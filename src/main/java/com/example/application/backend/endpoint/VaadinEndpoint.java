package com.example.application.backend.endpoint;

import com.example.application.backend.entity.Company;
import com.example.application.backend.service.CompanyService;
import com.vaadin.flow.server.connect.Endpoint;
import com.vaadin.flow.server.connect.auth.AnonymousAllowed;

@Endpoint
@AnonymousAllowed
public class VaadinEndpoint {

    private final CompanyService companyService;

    public VaadinEndpoint(CompanyService companyService) {
        this.companyService = companyService;
    }

    public Iterable<Company> getCompanies() {
        return companyService.getCompanies();
    }
}
