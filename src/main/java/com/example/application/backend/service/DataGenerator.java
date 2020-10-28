package com.example.application.backend.service;

import com.example.application.backend.entity.Company;
import com.example.application.backend.entity.Employee;
import com.example.application.backend.entity.Industry;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class DataGenerator {

    private static final int NUM_INDUSTRIES = 10;
    private static final int NUM_COMPANIES = 30;
    private static final int NUM_EMPLOYEES_MIN = 10;
    private static final int NUM_EMPLOYEES_MAX = 200;
    private static final int EMPLOYEE_REPORT_SIZE_CHARS = 4096;

    private final Random random = new Random();
    private final Faker faker;

    public DataGenerator(IndustryService industryService, CompanyService companyService) {
        this.faker = new Faker();

        Iterable<Industry> industries = generateIndustries(industryService);
        generateCompanies(industries, companyService);
    }

    private Iterable<Industry> generateIndustries(IndustryService industryService) {
        List<Industry> industries = IntStream.range(0, NUM_INDUSTRIES).mapToObj(i ->
                new Industry(faker.company().industry(), generateEmployeeReport())
        ).collect(Collectors.toList());
        return industryService.saveIndustries(industries);
    }

    private byte[] generateEmployeeReport() {
        return faker.lorem().characters(EMPLOYEE_REPORT_SIZE_CHARS).getBytes(StandardCharsets.UTF_8);
    }

    private void generateCompanies(Iterable<Industry> industries, CompanyService companyService) {
        companyService.saveCompanies(IntStream.range(0, NUM_COMPANIES).mapToObj(i -> {
            Industry industry = randomFrom(industries);
            List<Employee> employees = generateEmployees();
            String name = faker.company().name();
            String notes = faker.buffy().quotes();
            return new Company(name, employees, notes, industry);
        }).collect(Collectors.toList()));
    }

    private List<Employee> generateEmployees() {
        return IntStream.range(0, random.nextInt(NUM_EMPLOYEES_MAX - NUM_EMPLOYEES_MIN) + NUM_EMPLOYEES_MIN).mapToObj(i ->
            new Employee(faker.name().firstName(), faker.name().lastName(), faker.idNumber().invalidSvSeSsn())
        ).collect(Collectors.toList());
    }

    private <T> T randomFrom(Iterable<T> ts) {
        List<T> list = new ArrayList<>();
        ts.forEach(list::add);
        return list.get(random.nextInt(list.size()));
    }
}
