package com.example.application.backend.endpoint;

import com.example.application.backend.entity.Company;
import com.example.application.backend.entity.Employee;
import com.example.application.backend.service.CompanyService;
import com.example.application.backend.service.EmployeeService;
import com.vaadin.flow.server.connect.Endpoint;
import com.vaadin.flow.server.connect.auth.AnonymousAllowed;

@Endpoint
@AnonymousAllowed
public class VaadinEndpoint {

    private final CompanyService companyService;
    private final EmployeeService employeeService;

    public VaadinEndpoint(CompanyService companyService, EmployeeService employeeService) {
        this.companyService = companyService;
        this.employeeService = employeeService;
    }

    public Iterable<Company> getCompanies() {
        return companyService.getCompanies();
    }

    public Iterable<Employee> getEmployees(int companyId) {
        return employeeService.getEmployeesForCompany(companyId);
    }

    public void saveCompany(Company company) {
        // Must do this to avoid clearing out the employees
        company.getEmployees().forEach(employee -> employee.setCompany(company));
        companyService.saveCompany(company);
    }
}
