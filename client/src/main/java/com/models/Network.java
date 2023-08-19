package com.models;

import java.io.Serializable;

public class Network implements Serializable {
    String inst;
    String tg;
    String VK;

    public String getInst() {
        return inst;
    }

    public void setInst(String inst) {
        this.inst = inst;
    }

    public String getTg() {
        return tg;
    }

    public void setTg(String tg) {
        this.tg = tg;
    }

    public String getVK() {
        return VK;
    }

    public void setVK(String VK) {
        this.VK = VK;
    }
}
