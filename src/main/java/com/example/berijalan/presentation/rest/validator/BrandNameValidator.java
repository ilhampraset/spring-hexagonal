package com.example.berijalan.presentation.rest.validator;

import com.example.berijalan.core.entity.Brand;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

public class BrandNameValidator implements Validator {
    private static final String NORMAL_CHARACTER_REGEX = "^[a-zA-Z]+$";
    @Override
    public boolean supports(Class<?> clazz) {
        return Brand.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Brand brand = (Brand) target;
        if (!Pattern.matches(NORMAL_CHARACTER_REGEX, brand.getName()) || brand.getName().length() > 10) {
            errors.rejectValue("username", "username.min.length", "Username must be at least 5 characters long.");
        }
    }
}
