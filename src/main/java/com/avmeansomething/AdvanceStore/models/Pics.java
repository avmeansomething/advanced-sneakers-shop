package com.avmeansomething.AdvanceStore.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;

@Entity
public class Pics {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String url;
    private int sneakerid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getSneakerid() {
        return sneakerid;
    }

    public void setSneakerid(int sneakerid) {
        this.sneakerid = sneakerid;
    }

    public Pics(String url, int sneakerid) {
        this.url = url;
        this.sneakerid = sneakerid;
    }

    public Pics() {
    }
}
