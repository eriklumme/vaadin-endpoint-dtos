package com.example.application.backend.service;

import com.example.application.backend.dto.CompanyDTO;
import com.example.application.backend.entity.Company;
import com.example.application.backend.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final IndustryNameService industryNameService;

    public CompanyService(CompanyRepository companyRepository, IndustryNameService industryNameService) {
        this.companyRepository = companyRepository;
        this.industryNameService = industryNameService;
    }

    public List<CompanyDTO> getCompanies() {
        List<CompanyDTO> companyDTOS = new ArrayList<>();
        companyRepository.findByOrderByName().forEach(company -> companyDTOS.add(toDto(company)));
        return companyDTOS;
    }

    public void saveCompany(CompanyDTO companyDTO) {
        Optional<Company> optionalCompany = companyRepository.findById(companyDTO.getId());
        optionalCompany.ifPresent(company -> {
            company.setNotes(companyDTO.getNotes());
            companyRepository.save(company);
        });
    }

    public Iterable<Company> saveCompanies(Iterable<Company> companies) {
        return companyRepository.saveAll(companies);
    }

    private CompanyDTO toDto(Company company) {
        String industryName = industryNameService.getProperNameForIndustry(company.getIndustry().getName());
        return new CompanyDTO(
                company.getId(),
                company.getName(),
                industryName,
                company.getNotes());
    }
}
