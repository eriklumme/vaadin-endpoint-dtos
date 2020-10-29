package com.example.application.backend.service;

import org.springframework.stereotype.Service;

@Service
public class IndustryNameService {

    public String getProperNameForIndustry(String industry) {
        if (industry == null) {
            return null;
        }
        return String.valueOf(industry.charAt(0)) +
                industry.charAt(1) * industry.charAt(2) % 7 +
                industry.hashCode() % 37 +
                System.identityHashCode(industry) % 11 +
                " (" + industry + ")";
    }
}
