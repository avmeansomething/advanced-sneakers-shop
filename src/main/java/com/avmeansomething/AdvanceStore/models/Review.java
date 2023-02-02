package com.avmeansomething.AdvanceStore.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String review, name, mail;
    private int mark, idsneaker;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public Review() {
    }

    public Review(String review, String name, String mail, int mark, int idsneaker) {
        this.review = review;
        this.name = name;
        this.mail = mail;
        this.mark = mark;
        this.idsneaker = idsneaker;
    }

    public int getIdsneaker() {
        return idsneaker;
    }

    public void setIdsneaker(int idsneaker) {
        this.idsneaker = idsneaker;
    }
}
