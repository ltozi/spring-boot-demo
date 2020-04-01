package com.kaleyra.springbootdemo;

public class Patient {

    private long idHospital;
    String name;
    String city;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public long getIdHospital() {
        return idHospital;
    }

    public void setIdHospital(long idHospital) {
        this.idHospital = idHospital;
    }
}
