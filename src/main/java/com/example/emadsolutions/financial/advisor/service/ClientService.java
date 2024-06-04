package com.example.emadsolutions.financial.advisor.service;


import com.example.emadsolutions.financial.advisor.entity.Client;
import com.example.emadsolutions.financial.advisor.entity.Security;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    Client save(Client theClient);
    Client findClientByAdvisor(Long advisorId,Long clientId);
    Client updateClient(Client existingClient, Client newClient);

    Client findById(Long clientId);
    void deleteById(Long clientId);
    boolean validateAdvisorPermission(Client theClient ,Long advisorId);

    Security createSecurityForClient(Long advisorId,Long clientId, Security theSecurity);
    List<Security> getAllSecuritiesForClient(Long clientId);

}
