package com.example.emadsolutions.financial.advisor.service;

import com.example.emadsolutions.financial.advisor.dao.PortfolioReposotiry;
import com.example.emadsolutions.financial.advisor.entity.Portfolio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PortfolioServiceImpl implements PortfolioService{
    private final PortfolioReposotiry portfolioReposotiry;

    @Autowired
    public PortfolioServiceImpl(PortfolioReposotiry portfolioReposotiry)
    {
        this.portfolioReposotiry = portfolioReposotiry;
    }
    @Override
    public Portfolio findPortfolioByClient(Long clientId, Long portfolioId) {
        Portfolio portfolio = portfolioReposotiry.findByClientIdAndId(clientId, portfolioId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Portfolio with id - "+
                        portfolioId+" not found"));
        return portfolio;
    }
}
