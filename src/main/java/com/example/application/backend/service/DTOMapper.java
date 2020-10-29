package com.example.application.backend.service;

import com.example.application.backend.dto.CompanyDTO;
import com.example.application.backend.entity.Company;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.stereotype.Service;

@Service
public class DTOMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    public DTOMapper(IndustryNameService industryNameService) {
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);

        Converter<String, String> industryNameConverter = context ->
                industryNameService.getProperNameForIndustry(context.getSource());

        modelMapper.typeMap(Company.class, CompanyDTO.class)
                .addMappings(mapper ->
                        mapper.using(industryNameConverter).map(c -> c.getIndustry().getName(), CompanyDTO::setIndustryName));
    }

    public CompanyDTO map(Company company) {
        return modelMapper.map(company, CompanyDTO.class);
    }
}
