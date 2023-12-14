package com.example.berijalan.application;

import com.example.berijalan.core.entity.Brand;
import com.example.berijalan.core.repository.IBrandRepository;
import com.example.berijalan.core.service.IBrandService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

@Service
public class BrandApplicationService implements IBrandService
{


    private final IBrandRepository brandRepository;
    private static final String NORMAL_CHARACTER_REGEX = "^[a-zA-Z]+$";

    public BrandApplicationService(IBrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public List<Brand> all(String brandName) {
        if (Objects.equals(brandName, "")) {
            return brandRepository.findAll();
        }
        validateName(brandName);
        return brandRepository.findBrandByName(brandName);
    }

    private void validateName(String name) {
        if (!Pattern.matches(NORMAL_CHARACTER_REGEX, name) || name.length() > 10) {
            throw new IllegalArgumentException("illegal argument");
        }
    }
}
