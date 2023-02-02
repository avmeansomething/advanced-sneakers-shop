package com.avmeansomething.AdvanceStore.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Accessories {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String type, brand, name, pic, color, description, pictwo;
    private int cost, istock, ident;

    public Accessories(String type, String brand, String name, String pic, String color, String description, String pictwo, int cost, int istock) {
        this.type = type;
        this.brand = brand;
        this.name = name;
        this.pic = pic;
        this.color = color;
        this.description = description;
        this.pictwo = pictwo;
        this.cost = cost;
        this.istock = istock;
        this.ident = 0;
    }

    public Accessories() {

    }

    public int getIdent() {
        return ident;
    }

    public void setIdent(int ident) {
        this.ident = ident;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBrand() {
        return ident;
    }

    public void setBrand(Integer ident) {
        this.ident = ident;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
    public String getPictwo() {
        return pictwo;
    }

    public void setPictwo(String pictwo) {
        this.pictwo = pictwo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getIstock() {
        return istock;
    }

    public void setIstock(int istock) {
        this.istock = istock;
    }
}
