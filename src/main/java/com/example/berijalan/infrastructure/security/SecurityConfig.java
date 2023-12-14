package com.example.berijalan.infrastructure.security;

import com.example.berijalan.application.ApiKeyAplicationService;
import com.example.berijalan.infrastructure.security.filter.AuthenticationFilter;
import com.example.berijalan.infrastructure.security.filter.HeaderValidationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.XXssProtectionHeaderWriter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private ApiKeyAplicationService apiKeyService;

    public SecurityConfig(ApiKeyAplicationService apiKeyService) {
        this.apiKeyService = apiKeyService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.headers(headers ->
                headers.xssProtection(
                        xss -> xss.headerValue(XXssProtectionHeaderWriter.HeaderValue.ENABLED_MODE_BLOCK)
                ).httpStrictTransportSecurity(hsts ->
                        hsts.includeSubDomains(true).preload(true).maxAgeInSeconds(365)
                ))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/**").permitAll()
                        .anyRequest().authenticated()
                ).csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(new HeaderValidationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new AuthenticationFilter(apiKeyService), HeaderValidationFilter.class);
        return http.build();
    }


}