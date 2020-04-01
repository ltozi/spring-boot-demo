package com.kaleyra.springbootdemo;

public class Patient {

    public long getPatientId() {
        return patientId;
    }

    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }

    private long patientId;

    String name;
    String email;
    String hospital;

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
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



    @Override
    public String toString() {
        return "Patient{" +
                "id=" + patientId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", hospital='" + hospital + '\'' +
                '}';
    }
}
