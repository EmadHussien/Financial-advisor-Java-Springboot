package com.example.emadsolutions.financial.advisor.rest;

import com.example.emadsolutions.financial.advisor.dao.AdvisorReposotiry;
import com.example.emadsolutions.financial.advisor.dao.ClientReposotiry;
import com.example.emadsolutions.financial.advisor.dao.PortfolioReposotiry;
import com.example.emadsolutions.financial.advisor.dao.SecurityReposotiry;
import com.example.emadsolutions.financial.advisor.entity.Advisor;
import com.example.emadsolutions.financial.advisor.entity.Client;
import com.example.emadsolutions.financial.advisor.entity.Portfolio;
import com.example.emadsolutions.financial.advisor.entity.Security;
import com.example.emadsolutions.financial.advisor.service.AdvisorService;
import com.example.emadsolutions.financial.advisor.service.ClientService;
import com.example.emadsolutions.financial.advisor.service.PortfolioService;
import com.example.emadsolutions.financial.advisor.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
public class AdvisorRestController {


    private final AdvisorService advisorService;
    private final ClientService clientService;

    private final PortfolioService portfolioService;
    private final SecurityService securityService;

    @Autowired
    public AdvisorRestController(
            AdvisorService advisorService,ClientService clientService,PortfolioService portfolioService,
            SecurityService securityService

    ) {

        this.advisorService = advisorService;
        this.clientService = clientService;
        this.portfolioService = portfolioService;
        this.securityService = securityService;
    }

    // TO BE DELETED
  /*
    @GetMapping("/advisors")
    public List<Advisor> getAdvisors()
    {
        return advisorReposotiry.findAll();
    }
  */
    // create client associated to an advisor
    @PostMapping("advisors/{advisorId}/clients")
    public ResponseEntity<?> createClient(@PathVariable long advisorId,  @RequestBody Client theClient)
    {
        // find the corresponding advisor and set the client advisor to it
        Advisor advisor = advisorService.findById(advisorId);
        theClient.setAdvisor(advisor);

        // persist the client
        Client savedClient = clientService.save(theClient);

        return new ResponseEntity<>(savedClient, HttpStatus.CREATED);
    }
    @GetMapping("advisors/{advisorId}/clients")
    public List<Client> getAdvisorClients(@PathVariable long advisorId)
    {
        // find the advisor by id
        Advisor advisor = advisorService.findById(advisorId);

        // return the list of clients
        return advisor.getClients();
    }
    @PutMapping("advisors/{advisorId}/clients/{clientId}")
    public ResponseEntity<?> updateClient(@PathVariable long advisorId, @PathVariable long clientId, @RequestBody Client theClient)
    {
        // find the advisor
        Advisor advisor = advisorService.findById(advisorId);

        // find the client associated with the advisor
        Client foundClient = clientService.findClientByAdvisor(advisorId,clientId);

        // update the client
        Client updatedClient = clientService.updateClient(foundClient,theClient);

        return new ResponseEntity<>(updatedClient, HttpStatus.OK);
    }

    @DeleteMapping("advisors/{advisorId}/clients/{clientId}")
    public ResponseEntity<?> deleteClient(@PathVariable Long advisorId,@PathVariable Long clientId)
    {

        Client client = clientService.findById(clientId);

        if(clientService.validateAdvisorPermission(client ,advisorId))
        {
            clientService.deleteById(clientId);
        }
        else
        {
            return new ResponseEntity<>("You don't have permission",HttpStatus.UNAUTHORIZED);

        }
        return new ResponseEntity<>("Client with id - "+clientId+" is deleted successfully",HttpStatus.OK);
    }

    @PostMapping("advisors/{advisorId}/clients/{clientId}/securities")
    public ResponseEntity<?> createClientSecurity(
            @PathVariable Long advisorId ,
            @PathVariable Long clientId ,
            @RequestBody Security theSecurity
            )
    {
        Security createdSecurity = clientService.createSecurityForClient(advisorId,clientId,theSecurity);
        //return the security
        return new ResponseEntity<>(createdSecurity,HttpStatus.CREATED);
    }

    @GetMapping("advisors/{advisorId}/clients/{clientId}/securities")
    public ResponseEntity<?> getAllSecuritiesForClient(
            @PathVariable Long clientId
    )
    {
        List<Security> allSecuritiesForClient = clientService.getAllSecuritiesForClient(clientId);
        return new ResponseEntity<>(allSecuritiesForClient,HttpStatus.OK);
    }


    @PutMapping("advisors/{advisorId}/clients/{clientId}/portfolios/{portfolioId}/securities/{securityId}")
    public ResponseEntity<?> updateSecurity(
            @PathVariable Long clientId,
            @PathVariable Long portfolioId ,
            @PathVariable Long securityId,
            @RequestBody Security theSecurity
    )
    {
        Client theClient = clientService.findById(clientId);

        Portfolio portfolio = portfolioService.findPortfolioByClient(clientId,portfolioId);

        Security foundSecurity = securityService.findSecurityByPortfolio(portfolioId,securityId);

        Security updatedSecurity = securityService.updateSecurity(foundSecurity,theSecurity);

        return new ResponseEntity<>(updatedSecurity,HttpStatus.OK);
    }

    @DeleteMapping("advisors/{advisorId}/clients/{clientId}/portfolios/{portfolioId}/securities/{securityId}")
    public ResponseEntity<?> deleteSecurity(
            @PathVariable Long clientId,
            @PathVariable Long portfolioId ,
            @PathVariable Long securityId
    )
    {
        Client theClient = clientService.findById(clientId);

        Portfolio portfolio = portfolioService.findPortfolioByClient(clientId, portfolioId);

        Security foundSecurity = securityService.findSecurityByPortfolio(portfolioId, securityId);

        securityService.delete(foundSecurity);

        return new ResponseEntity<>("Security with id - "+securityId+" is deleted successfully",HttpStatus.OK);


    }


}
