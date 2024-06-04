package com.example.emadsolutions.financial.advisor;

import com.example.emadsolutions.financial.advisor.dao.AdvisorReposotiry;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FinancialAdvisorApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinancialAdvisorApplication.class, args);

	}
}
