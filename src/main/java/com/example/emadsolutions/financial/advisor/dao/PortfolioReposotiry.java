package com.example.emadsolutions.financial.advisor.dao;

import com.example.emadsolutions.financial.advisor.entity.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PortfolioReposotiry extends JpaRepository<Portfolio,Long> {
    Optional<Portfolio> findByClientIdAndId(Long clientId, Long portfolioId);
}
