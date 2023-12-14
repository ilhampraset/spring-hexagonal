package com.example.berijalan.application;

import com.example.berijalan.core.entity.ApiKey;
import com.example.berijalan.core.repository.IApiKeyRepository;
import com.example.berijalan.core.service.IApiKeyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiKeyAplicationService implements IApiKeyService {
    private final IApiKeyRepository apiKeyRepository;

    public ApiKeyAplicationService(IApiKeyRepository apiKeyRepository) {
        this.apiKeyRepository = apiKeyRepository;
    }

    @Override
    public String findApiKey(String key) {
        List<ApiKey> data = apiKeyRepository.findBrandByKey(key);
        if (!data.isEmpty()) {
            return data.get(0).getKey();
        }
        return "";
    }
}
