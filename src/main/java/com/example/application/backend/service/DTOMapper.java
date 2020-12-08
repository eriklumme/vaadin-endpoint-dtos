package com.example.application.backend.service;

import com.example.application.backend.dto.CompanyDTO;
import com.example.application.backend.entity.Company;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.config.Configuration;
import org.modelmapper.spi.PropertyInfo;
import org.modelmapper.spi.PropertyMapping;
import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Collectors;

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

    public <S, D> void printValidations(Class<S> sourceClass, Class<D> destinationClass) {
        TypeMap<S, D> typeMap = modelMapper.getTypeMap(sourceClass, destinationClass);
        typeMap.getMappings().stream()
                .filter(PropertyMapping.class::isInstance)
                .map(PropertyMapping.class::cast)
                .forEach(this::printValidations);
    }

    private void printValidations(PropertyMapping propertyMapping) {
        PropertyInfo sourceProperty = propertyMapping.getLastSourceProperty();
        PropertyInfo destinationProperty = propertyMapping.getLastDestinationProperty();

        Class<?> sourceClass = sourceProperty.getInitialType();
        Class<?> destinationClass = destinationProperty.getInitialType();

        Field sourceField = findFieldInClassOrSuper(sourceClass, sourceProperty.getName());
        Annotation[] sourceAnnotations = sourceField.getAnnotations();
        String annotationString = sourceAnnotations.length == 0 ?
                "no annotations" :
                "annotations " + Arrays.stream(sourceAnnotations)
                        .map(annotation -> annotation.annotationType().getName())
                        .collect(Collectors.joining(", "));

        System.out.printf("%s.%s was mapped from %s.%s which had %s%n",
                destinationClass.getSimpleName(), destinationProperty.getName(),
                sourceClass.getSimpleName(), sourceProperty.getName(),
                annotationString);
    }

    private Field findFieldInClassOrSuper(Class<?> type, String fieldName) {
        Class<?> currentType = type;
        while (currentType != null) {
            try {
                return currentType.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                currentType = currentType.getSuperclass();
            }
        }
        throw new RuntimeException(String.format("Could not find field %s in type %s%n", fieldName, type));
    }
}
