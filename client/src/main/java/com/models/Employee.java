package com.models;

import java.io.Serializable;

public class Employee implements Serializable {
    int id;
    String photo;
    String name;
    String last_name;
    String patronymic;
    String phone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getId_passport() {
        return id_passport;
    }

    public void setId_passport(String id_passport) {
        this.id_passport = id_passport;
    }

    public String getSeria_number() {
        return seria_number;
    }

    public void setSeria_number(String seria_number) {
        this.seria_number = seria_number;
    }

    public String getDate_registration() {
        return date_registration;
    }

    public void setDate_registration(String date_registration) {
        this.date_registration = date_registration;
    }

    String position;
    String id_passport;
    String seria_number;
    String date_registration;

}
