package com.avmeansomething.AdvanceStore.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private int idsneaker, idaccessory;
    private String name, email, topic, text;

    public Feedback(String name, String email, String topic, String text) {
        this.name = name;
        this.email = email;
        this.topic = topic;
        this.text = text;
    }

    public Feedback() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
