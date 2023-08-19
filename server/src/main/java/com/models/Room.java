package com.models;

import java.io.Serializable;
import java.util.List;

public class Room implements Serializable {
    int id;
    String type;
    List<PhotoRoom> listPhoto;
    String price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<PhotoRoom> getListPhoto() {
        return listPhoto;
    }

    public void setListPhoto(List<PhotoRoom> listPhoto) {
        this.listPhoto = listPhoto;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    String status;
    String description;
}
