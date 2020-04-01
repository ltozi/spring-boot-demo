package com.kaleyra.springbootdemo;

public class Hospital {

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

    @Override
    public String toString() {
        return "User{" +
                "id=" + idHospital +
                ", name='" + name + '\'' +
                ", email='" + city + '\'' +
                '}';
    }

}
