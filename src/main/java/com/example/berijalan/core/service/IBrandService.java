package com.example.berijalan.core.service;


import com.example.berijalan.core.entity.Brand;

import java.util.List;

public interface IBrandService {
    List<Brand> all(String brandName);
}
