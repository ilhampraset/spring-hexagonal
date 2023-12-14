package com.example.berijalan.core.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

@Entity
@Table(name = "brands")
public class Brand {
    @jakarta.persistence.Id

    @JsonProperty("CD_BRAND")
    private String code;
    @JsonProperty("DESC_BRAND")
    private String name;

    public void setCode(String code) {
        this.code = code;
    }
    public String getCode() {
        return this.code;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

}
