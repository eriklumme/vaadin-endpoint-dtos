package com.example.application.backend.service;

import com.example.application.backend.entity.Industry;
import com.example.application.backend.repository.IndustryRepository;
import org.springframework.stereotype.Service;

@Service
public class IndustryService {

    private final IndustryRepository industryRepository;

    public IndustryService(IndustryRepository industryRepository) {
        this.industryRepository = industryRepository;
    }

    public Iterable<Industry> saveIndustries(Iterable<Industry> industries) {
        return industryRepository.saveAll(industries);
    }
}
