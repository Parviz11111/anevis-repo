package com.assignment3.h2setup.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PieData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String country;
    double weight;

    public PieData(String country, double weight) {
        this.country = country;
        this.weight = weight;
    }

    public Long getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public double getWeight() {
        return weight;
    }
}
