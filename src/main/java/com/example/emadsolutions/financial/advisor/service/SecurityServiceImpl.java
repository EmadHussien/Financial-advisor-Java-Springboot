package com.example.emadsolutions.financial.advisor.service;

import com.example.emadsolutions.financial.advisor.dao.SecurityReposotiry;
import com.example.emadsolutions.financial.advisor.entity.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class SecurityServiceImpl implements SecurityService{

    private final SecurityReposotiry securityReposotiry;

    @Autowired
    public SecurityServiceImpl(SecurityReposotiry securityReposotiry)
    {
        this.securityReposotiry = securityReposotiry;
    }

    @Override
    public Security save(Security theSecurity) {
        return  securityReposotiry.save(theSecurity);
    }

    @Override
    public void delete(Security theSecurity) {
        securityReposotiry.delete(theSecurity);
    }

    @Override
    public Security findSecurityByPortfolio(Long portfolioId, Long securityId) {
        Security foundSecurity = securityReposotiry.findByPortfolioIdAndId(portfolioId, securityId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Security with id - "+
                        securityId+" not found"));
        return  foundSecurity;
    }

    @Override
    public Security updateSecurity(Security existingSecurity, Security newSecurity) {

        existingSecurity.setName(newSecurity.getName());
        existingSecurity.setCategory(newSecurity.getCategory());
        existingSecurity.setQuantity(newSecurity.getQuantity());
        existingSecurity.setPurshasePrice(newSecurity.getPurshasePrice());

        Security updatedSecurity = save(existingSecurity);
        return updatedSecurity;
    }
}
