package com.example.application.backend.repository;

import com.example.application.backend.entity.Industry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndustryRepository extends CrudRepository<Industry, Integer> {
}
