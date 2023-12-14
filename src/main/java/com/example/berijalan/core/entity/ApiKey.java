package com.example.berijalan.core.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
@Entity
@Table(name = "api_key")
public class ApiKey {
    @jakarta.persistence.Id

    private String key;
    public String getKey() {
        return key;
    }
}
