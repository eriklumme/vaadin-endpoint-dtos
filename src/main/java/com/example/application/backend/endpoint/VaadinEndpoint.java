package com.example.application.backend.endpoint;

import com.example.application.backend.dto.CompanyDTO;
import com.example.application.backend.dto.EmployeeDTO;
import com.example.application.backend.entity.Company;
import com.example.application.backend.service.CompanyService;
import com.example.application.backend.service.DTOMapper;
import com.example.application.backend.service.EmployeeService;
import com.vaadin.flow.server.connect.Endpoint;
import com.vaadin.flow.server.connect.auth.AnonymousAllowed;

import java.util.List;
import java.util.stream.Collectors;

@Endpoint
@AnonymousAllowed
public class VaadinEndpoint {

    private final CompanyService companyService;
    private final EmployeeService employeeService;
    private final DTOMapper dtoMapper;

    public VaadinEndpoint(CompanyService companyService, EmployeeService employeeService, DTOMapper dtoMapper) {
        this.companyService = companyService;
        this.employeeService = employeeService;
        this.dtoMapper = dtoMapper;
    }

    public List<CompanyDTO> getCompanies() {
        dtoMapper.printValidations(Company.class, CompanyDTO.class);
        return companyService.getCompanies().stream().map(dtoMapper::map).collect(Collectors.toList());
    }

    public List<EmployeeDTO> getEmployees(int companyId) {
        return employeeService.getEmployeesForCompany(companyId);
    }

    public void saveCompany(CompanyDTO companyDTO) {
        companyService.saveCompany(companyDTO);
    }
}
