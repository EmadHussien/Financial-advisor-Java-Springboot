package com.example.emadsolutions.financial.advisor.service;

import com.example.emadsolutions.financial.advisor.entity.Client;
import com.example.emadsolutions.financial.advisor.entity.Portfolio;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public interface PortfolioService {
    Portfolio findPortfolioByClient(Long clientId, Long portfolioId);

}
