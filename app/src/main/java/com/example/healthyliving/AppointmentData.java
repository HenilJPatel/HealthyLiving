package com.example.healthyliving;

import androidx.annotation.NonNull;

public class AppointmentData {
    private String PatientName;
    private String DoctorName;
    private String Time;
    private String Address;
    private long id;
    private String status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPatientName() {
        return PatientName;
    }

    public void setPatientName(String patientName) {
        PatientName = patientName;
    }

    public String getDoctorName() {
        return DoctorName;
    }

    public void setDoctorName(String doctorName) {
        DoctorName = doctorName;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public AppointmentData(){  }

    @NonNull
    @Override
    public String toString() {
        return  "Appointment "+ getId() + " with " + DoctorName +"\n"+
                "on " + Time + '\n' +
                "at " + Address + "";
    }
}
