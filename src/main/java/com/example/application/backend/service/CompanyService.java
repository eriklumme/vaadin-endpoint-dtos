package com.example.application.backend.service;

import com.example.application.backend.entity.Company;
import com.example.application.backend.repository.CompanyRepository;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Iterable<Company> getCompanies() {
        return companyRepository.findByOrderByName();
    }

    public Company saveCompany(Company company) {
        return companyRepository.save(company);
    }

    public Iterable<Company> saveCompanies(Iterable<Company> companies) {
        return companyRepository.saveAll(companies);
    }
}
