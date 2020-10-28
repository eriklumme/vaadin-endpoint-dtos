package com.example.application.backend.repository;

import com.example.application.backend.entity.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

    Iterable<Employee> findEmployeesByCompanyId(int companyId);
}
