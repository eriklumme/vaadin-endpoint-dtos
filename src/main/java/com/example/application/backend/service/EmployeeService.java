package com.example.application.backend.service;

import com.example.application.backend.dto.EmployeeDTO;
import com.example.application.backend.entity.Employee;
import com.example.application.backend.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<EmployeeDTO> getEmployeesForCompany(int companyId) {
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        employeeRepository.findEmployeesByCompanyIdOrderByFirstNameAscLastNameAsc(companyId)
                .forEach(employee -> employeeDTOS.add(toDto(employee)));
        return employeeDTOS;
    }

    private EmployeeDTO toDto(Employee employee) {
        return new EmployeeDTO(employee.getFirstName(), employee.getLastName());
    }
}
