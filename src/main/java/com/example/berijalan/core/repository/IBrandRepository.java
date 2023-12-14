package com.example.berijalan.core.repository;

import com.example.berijalan.core.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IBrandRepository extends JpaRepository<Brand, Long> {
    List<Brand> findBrandByName(@Param("name") String name);
}

