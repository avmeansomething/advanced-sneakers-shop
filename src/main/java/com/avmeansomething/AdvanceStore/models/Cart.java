package com.avmeansomething.AdvanceStore.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private int idsneaker, idaccessory;

    public Cart(int idsneaker, int idaccessory) {
        this.idsneaker = idsneaker;
        this.idaccessory = idaccessory;
    }

    public Cart(){}

    public int getIdsneaker() {
        return idsneaker;
    }

    public void setIdsneaker(int idsneaker) {
        this.idsneaker = idsneaker;
    }

    public int getIdaccessory() {
        return idaccessory;
    }

    public void setIdaccessory(int idaccessory) {
        this.idaccessory = idaccessory;
    }
}
