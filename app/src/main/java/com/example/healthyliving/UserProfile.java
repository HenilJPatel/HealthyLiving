package com.example.healthyliving;

public class UserProfile {
    private String name=null;
    private String DateOfBirth=null;
    private String HealthInsuranceNo=null;
    private String PrimaryDoctor=null;
    private String SSN=null;
    private String Address=null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    public String getHealthInsuranceNo() {
        return HealthInsuranceNo;
    }

    public void setHealthInsuranceNo(String healthInsuranceNo) {
        HealthInsuranceNo = healthInsuranceNo;
    }

    public String getPrimaryDoctor() {
        return PrimaryDoctor;
    }

    public void setPrimaryDoctor(String primaryDoctor) {
        PrimaryDoctor = primaryDoctor;
    }

    public String getSSN() {
        return SSN;
    }

    public void setSSN(String SSN) {
        this.SSN = SSN;
    }

    public UserProfile() {
    }

    public UserProfile(String name, String dateOfBirth, String healthInsuranceNo, String primaryDoctor, String SSN, String address) {
        this.name = name;
        this.DateOfBirth = dateOfBirth;
        this.HealthInsuranceNo = healthInsuranceNo;
        this.PrimaryDoctor = primaryDoctor;
        this.SSN = SSN;
        this.Address = address;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                ", name='" + name + '\'' +
                ", DateOfBirth='" + DateOfBirth + '\'' +
                ", HealthInsuranceNo='" + HealthInsuranceNo + '\'' +
                ", PrimaryDoctor='" + PrimaryDoctor + '\'' +
                ", SSN='" + SSN + '\'' +
                ", Address='" + Address + '\'' +
                '}';
    }
}
