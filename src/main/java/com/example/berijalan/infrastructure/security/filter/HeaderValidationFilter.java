package com.example.berijalan.infrastructure.security.filter;

import com.example.berijalan.presentation.rest.response.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

public class HeaderValidationFilter extends GenericFilterBean {

    private static final List<String> REQUIRED_HEADERS = Arrays.asList(
            "X-XSS-Protection",
            "X-Frame-Options",
            "X-Content-Type-Options",
            "Strict-Transport-Security"
    );

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        for (String header : REQUIRED_HEADERS) {
            String headerValue = httpRequest.getHeader(header);
            if (headerValue == null || headerValue.isEmpty()) {
                HttpServletResponse httpResponse = (HttpServletResponse) response;
                httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                httpResponse.setContentType("application/json");
                ApiResponse jsonResponse = new ApiResponse();
                jsonResponse.setMessage("you do not have permission to access the API!");
                jsonResponse.setStatus("F");
                ObjectMapper objectMapper = new ObjectMapper();
                PrintWriter writer = httpResponse.getWriter();
                objectMapper.writeValue(writer, jsonResponse);
            }
        }
        chain.doFilter(request, response);
    }

}
