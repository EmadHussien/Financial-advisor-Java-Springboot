package com.example.emadsolutions.financial.advisor.service;

import com.example.emadsolutions.financial.advisor.entity.Client;
import com.example.emadsolutions.financial.advisor.entity.Security;

public interface SecurityService {
    Security save(Security theSecurity);
    void delete(Security theSecurity);

    Security findSecurityByPortfolio(Long portfolioId, Long securityId);
    Security updateSecurity(Security existingSecurity, Security newSecurity);
}
