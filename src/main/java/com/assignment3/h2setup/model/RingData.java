package com.assignment3.h2setup.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RingData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private final String date;
    private final String security;
    private final double weighting;

    public RingData(String date, String security, double weighting) {
        this.date = date;
        this.security = security;
        this.weighting = weighting;
    }

    public Long getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getSecurity() {
        return security;
    }

    public double getWeighting() {
        return weighting;
    }
}
