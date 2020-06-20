package com.example.thi1;

public class restaurant {
    int Ma;
    String name, local;
    Double rate;

    public restaurant(int ma, String name, String local, Double rate) {
        Ma = ma;
        this.name = name;
        this.local = local;
        this.rate = rate;
    }

    public restaurant() {
    }

    public int getMa() {
        return Ma;
    }

    public void setMa(int ma) {
        Ma = ma;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
}
