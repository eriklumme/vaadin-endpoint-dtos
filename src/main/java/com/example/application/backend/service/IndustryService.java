package com.example.application.backend.service;

import com.example.application.backend.entity.Industry;
import com.example.application.backend.repository.IndustryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IndustryService {

    private final IndustryRepository industryRepository;
    private final IndustryNameService industryNameService;

    public IndustryService(IndustryRepository industryRepository, IndustryNameService industryNameService) {
        this.industryRepository = industryRepository;
        this.industryNameService = industryNameService;
    }

    public Iterable<Industry> saveIndustries(Iterable<Industry> industries) {
        return industryRepository.saveAll(industries);
    }

    public String getRealNameForIndustry(int industryId) {
        Optional<Industry> industry = industryRepository.findById(industryId);
        return industry.map(value -> industryNameService.getProperNameForIndustry(value.getName())).orElse(null);
    }
}
