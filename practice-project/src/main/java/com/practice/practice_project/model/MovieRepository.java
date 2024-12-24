package com.practice.practice_project.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<MovieEntity, Long> {
    Optional<MovieEntity> findByName(String name);
    List<MovieEntity> findByNameContainingIgnoreCaseOrderByCreationDateDesc(String keyword);
    List<MovieEntity> findByRateIsNull();
}
