package com.example.application.backend.service;

import com.example.application.backend.entity.Employee;
import com.example.application.backend.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Iterable<Employee> getEmployeesForCompany(int companyId) {
        return employeeRepository.findEmployeesByCompanyId(companyId);
    }
}
