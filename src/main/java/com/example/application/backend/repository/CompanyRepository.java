package com.example.application.backend.repository;

import com.example.application.backend.entity.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Integer> {

    List<Company> findByOrderByName();
}
