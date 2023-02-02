package com.avmeansomething.AdvanceStore.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;



@Entity
public class Sneakers {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String brand;
    private String description;
    private String sizes;
    private String color;
    private String sex;
    private String type;
    private String material;
    private String pic;

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    private String brand_name;
    private int cost, isnew, issale, istock, idbrand;

    public Integer getIdbrand() {
        return idbrand;
    }

    public void setIdbrand(Integer idbrand) {
        this.idbrand = idbrand;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIstock() {
        return istock;
    }

    public void setIstock(Integer istock) {
        this.istock = istock;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getSizes() {
        return sizes;
    }

    public void setSizes(String sizes) {
        this.sizes = sizes;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getIsnew() {
        return isnew;
    }

    public void setIsnew(int isnew) {
        this.isnew = isnew;
    }

    public int getIssale() {
        return issale;
    }

    public void setIssale(int issale) {
        this.issale = issale;
    }

    public Sneakers(String brand, String brand_name, String description, String sizes, String color, String sex, String type, String material, String pic, int cost, int isnew, int issale, int istock) {
        this.brand = brand;
        this.brand_name = brand_name;
        this.description = description;
        this.sizes = sizes;
        this.color = color;
        this.sex = sex;
        this.type = type;
        this.material = material;
        this.pic = pic;
        this.cost = cost;
        this.isnew = isnew;
        this.issale = issale;
        this.istock = istock;
        this.idbrand = 0;
    }

    public Sneakers(){}
}
