package com.example.berijalan.core.repository;

import com.example.berijalan.core.entity.ApiKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IApiKeyRepository extends JpaRepository<ApiKey, String> {

    List<ApiKey> findBrandByKey(@Param("key") String key);
}