package com.example.emadsolutions.financial.advisor.dao;

import com.example.emadsolutions.financial.advisor.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientReposotiry extends JpaRepository<Client,Long> {
    Optional<Client> findByAdvisor_AdvisorIdAndId(Long advisorId, Long clientId);

}
