package com.models;

import java.io.Serializable;

public class Passport implements Serializable {
    String id_passport;

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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    String seria_number;
    Account account;
}
