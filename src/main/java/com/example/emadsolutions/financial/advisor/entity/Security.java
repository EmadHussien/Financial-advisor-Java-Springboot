package com.example.emadsolutions.financial.advisor.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Security {
    @Id
    @GeneratedValue()
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String category;
    @Column(nullable = false)
    private double purshasePrice;
    @Column(nullable = false)
    private LocalDateTime purshaseDate;
    @Column(nullable = false)
    private int quantity;

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }


    @JsonBackReference
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE , CascadeType.PERSIST , CascadeType.REFRESH})
    @JoinColumn(name = "portfolio_id")
    private Portfolio portfolio;

    public Security() {
    }

    public Security(String name, String category, double purshasePrice, int quantity) {
        this.name = name;
        this.category = category;
        this.purshasePrice = purshasePrice;
        this.quantity = quantity;
    }




    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPurshasePrice() {
        return purshasePrice;
    }

    public void setPurshasePrice(double purshasePrice) {
        this.purshasePrice = purshasePrice;
    }

    public LocalDateTime getPurshaseDate() {
        return purshaseDate;
    }

    public void setPurshaseDate(LocalDateTime purshaseDate) {
        this.purshaseDate = purshaseDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @PrePersist
    public void prePersist() {
        purshaseDate = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Security{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", purshasePrice=" + purshasePrice +
                ", purshaseDate=" + purshaseDate +
                ", quantity=" + quantity +
                '}';
    }
}
