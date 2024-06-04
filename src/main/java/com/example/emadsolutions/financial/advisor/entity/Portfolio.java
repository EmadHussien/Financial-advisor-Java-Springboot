package com.example.emadsolutions.financial.advisor.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Portfolio {

    @Id
    @GeneratedValue()
    private Long id;
    @Column(nullable = false)
    private LocalDateTime timestamp;


    @JsonBackReference
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE , CascadeType.PERSIST , CascadeType.REFRESH})
    @JoinColumn(name = "client_id")
    private Client client;


    @JsonManagedReference
    @OneToMany(mappedBy = "portfolio",cascade = CascadeType.ALL)
    private List<Security> securities;

    public Portfolio() {
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @PrePersist
    public void prePersist() {
        timestamp = LocalDateTime.now();
    }

    public List<Security> getSecurities() {
        return securities;
    }

    public void setSecurities(List<Security> securities) {
        this.securities = securities;
    }

    public void addSecurity(Security theSecurity)
    {
        if(securities == null)
        {
            securities = new ArrayList<>();
        }
        securities.add(theSecurity);
        theSecurity.setPortfolio(this);
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Portfolio{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                ", securities=" + securities +
                '}';
    }
}
