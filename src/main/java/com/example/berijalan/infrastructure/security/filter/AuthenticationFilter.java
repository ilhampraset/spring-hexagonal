package com.example.berijalan.infrastructure.security.filter;

import com.example.berijalan.core.service.IApiKeyService;
import com.example.berijalan.infrastructure.security.authentication.ApiKeyAuthentication;
import com.example.berijalan.presentation.rest.response.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AuthenticationFilter extends GenericFilterBean {

    private static IApiKeyService apiKeyService;
    private static final String AUTH_TOKEN_HEADER_NAME = "APIKey";

    public AuthenticationFilter(IApiKeyService apiKeyService) {
        AuthenticationFilter.apiKeyService = apiKeyService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {

        try {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            SecurityContextHolder.getContext().setAuthentication(getAuthentication(httpRequest));
        } catch (Exception exp) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
            ApiResponse jsonResponse = new ApiResponse();
            jsonResponse.setMessage("you do not have permission to access the API!");
            jsonResponse.setStatus("F");
            ObjectMapper objectMapper = new ObjectMapper();
            PrintWriter writer = httpResponse.getWriter();
            objectMapper.writeValue(writer, jsonResponse);
        }

        filterChain.doFilter(request, response);
    }
    private static Authentication getAuthentication(HttpServletRequest request) {
        String apiKey = request.getHeader(AUTH_TOKEN_HEADER_NAME);
        if (apiKey == null || !apiKey.equals(apiKeyService.findApiKey(apiKey))) {
            throw new BadCredentialsException("Invalid API Key");
        }
        return new ApiKeyAuthentication(apiKey, AuthorityUtils.NO_AUTHORITIES);
    }
}
