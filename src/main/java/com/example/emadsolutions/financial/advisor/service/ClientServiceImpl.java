package com.example.emadsolutions.financial.advisor.service;

import com.example.emadsolutions.financial.advisor.dao.ClientReposotiry;
import com.example.emadsolutions.financial.advisor.entity.Advisor;
import com.example.emadsolutions.financial.advisor.entity.Client;
import com.example.emadsolutions.financial.advisor.entity.Portfolio;
import com.example.emadsolutions.financial.advisor.entity.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService{

    private final ClientReposotiry clientReposotiry;
    @Autowired
    public ClientServiceImpl(ClientReposotiry clientReposotiry) {
        this.clientReposotiry = clientReposotiry;
    }


    @Override
    public Client save(Client theClient) {
        return clientReposotiry.save(theClient);
    }

    @Override
    public Client findClientByAdvisor(Long advisorId, Long clientId) {
        Client client = clientReposotiry.findByAdvisor_AdvisorIdAndId(advisorId, clientId)
                .orElseThrow(() -> new RuntimeException("Client Not Found with id - "+ clientId));
        return client;
    }

    @Override
    public Client updateClient(Client existingClient, Client newClient) {

        existingClient.setFirstName(newClient.getFirstName());
        existingClient.setLastName(newClient.getLastName());
        existingClient.setAddress(newClient.getAddress());
        existingClient.setEmail(newClient.getEmail());
        existingClient.setPhone(newClient.getPhone());
        // Saving updated client details to the database
        return clientReposotiry.save(existingClient);

    }

    @Override
    public Client findById(Long clientId) {
        Optional<Client> client = clientReposotiry.findById(clientId);
        if (!client.isPresent()) {
            throw new RuntimeException("Did not find Advisor with the id - "+ clientId);
        }
        return client.get();

    }

    @Override
    public void deleteById(Long clientId) {
        clientReposotiry.deleteById(clientId);
    }

    @Override
    public boolean validateAdvisorPermission(Client theClient ,Long advisorId) {
        return  Objects.equals(theClient.getAdvisor().getAdvisorId(), advisorId);
    }

    @Override
    public Security createSecurityForClient(Long advisorId, Long clientId, Security theSecurity) {

        // find the client with client id
        Client theClient = findById(clientId);
        // add securty to the client list
            // create portfolio
            // add security to it
            // associate the portfolio to the client
        Portfolio thePortfolio = new Portfolio();
        thePortfolio.addSecurity(theSecurity);

        theClient.addPortfolios(thePortfolio);
        // save client
        Client savedClient = save(theClient);

        // Get the last portfolio's securities list
        List<Security> securities = savedClient.getPortfolios().get(savedClient.getPortfolios().size() - 1).getSecurities();

        // Get the last added security
        Security lastAddedSecurity = securities.get(securities.size() - 1);

        return lastAddedSecurity;
    }

    @Override
    public List<Security> getAllSecuritiesForClient(Long clientId) {
        Client theClient = findById(clientId);

        List<Security> allSecuritiesForClient = new ArrayList<>();
        for(Portfolio portfolio : theClient.getPortfolios())
        {
            List<Security> securities = portfolio.getSecurities();
            allSecuritiesForClient.addAll(securities);
        }

        return allSecuritiesForClient;
    }
}
