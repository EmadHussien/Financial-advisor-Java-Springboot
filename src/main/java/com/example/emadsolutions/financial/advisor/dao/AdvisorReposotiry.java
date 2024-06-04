package com.example.emadsolutions.financial.advisor.dao;

import com.example.emadsolutions.financial.advisor.entity.Advisor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvisorReposotiry extends JpaRepository<Advisor,Long> {
}
