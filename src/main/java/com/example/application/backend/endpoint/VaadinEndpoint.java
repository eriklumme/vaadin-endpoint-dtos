package com.example.application.backend.endpoint;

import com.example.application.backend.dto.CompanyDTO;
import com.example.application.backend.dto.EmployeeDTO;
import com.example.application.backend.service.CompanyService;
import com.example.application.backend.service.EmployeeService;
import com.vaadin.flow.server.connect.Endpoint;
import com.vaadin.flow.server.connect.auth.AnonymousAllowed;

import java.util.List;

@Endpoint
@AnonymousAllowed
public class VaadinEndpoint {

    private final CompanyService companyService;
    private final EmployeeService employeeService;

    public VaadinEndpoint(CompanyService companyService, EmployeeService employeeService) {
        this.companyService = companyService;
        this.employeeService = employeeService;
    }

    public List<CompanyDTO> getCompanies() {
        return companyService.getCompanies();
    }

    public List<EmployeeDTO> getEmployees(int companyId) {
        return employeeService.getEmployeesForCompany(companyId);
    }

    public void saveCompany(CompanyDTO companyDTO) {
        companyService.saveCompany(companyDTO);
    }
}
