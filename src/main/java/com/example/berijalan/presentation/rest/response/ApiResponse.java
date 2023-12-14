package com.example.berijalan.presentation.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class ApiResponse {
    @JsonProperty("OUT_STAT")
    private String status;
    @JsonProperty("OUT_MESS")
    private String message;
    @JsonProperty("OUT_DATA")
    private List<Object> data;


    public ApiResponse() {
        this.status = "";
        this.message = "";
        this.data = new ArrayList<>();
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }


}
