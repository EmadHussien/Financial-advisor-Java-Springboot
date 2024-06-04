package com.example.emadsolutions.financial.advisor.dao;

import com.example.emadsolutions.financial.advisor.entity.Security;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SecurityReposotiry extends JpaRepository<Security,Long> {

    Optional<Security> findByPortfolioIdAndId(Long portfolioId, Long securityId);
}
