package com.example.berijalan.presentation.rest;


import com.example.berijalan.core.entity.Brand;
import com.example.berijalan.core.service.IApiKeyService;
import com.example.berijalan.core.service.IBrandService;
import com.example.berijalan.presentation.rest.response.ApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;
import java.util.List;


@RestController
public class BrandController {
    private final IBrandService brandService;
    public BrandController(IBrandService brandService) {
        this.brandService = brandService;
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/brands")
    ResponseEntity<ApiResponse> all(@RequestBody String rawBody) {
        ApiResponse response = new ApiResponse();
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(rawBody);
            JsonNode pSearchNode = jsonNode.path("getListFilterUnitBrand").path("P_SEARCH");
            String searchParam = pSearchNode.asText();
            List<Brand> listData;
            listData = brandService.all(searchParam);
            if (!listData.isEmpty()) {
                response.setData(Collections.singletonList(listData));
            }
            response.setStatus("T");
            response.setMessage("SUCCESS");

        } catch (IllegalArgumentException | JsonProcessingException illegal){
            httpStatus = HttpStatus.BAD_REQUEST;
            response.setStatus("F");
            response.setMessage("Invalid Input");
        }
        return new ResponseEntity<>(
                response, httpStatus);
    }
}

