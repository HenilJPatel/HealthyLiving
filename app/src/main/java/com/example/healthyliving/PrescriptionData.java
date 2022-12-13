package com.example.healthyliving;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PrescriptionData {


    private Long presID;
    private String lastrefilldate;
    private Long refill;
    private String productID;
    private Long doctorID;
    private Long daysofsupply;

    public PrescriptionData() {
    }

    public Long getPresID() {
        return presID;
    }

    public void setPresID(Long presID) {
        this.presID = presID;
    }

    public String getLastrefilldate() {
        return lastrefilldate;
    }

    public void setLastrefilldate(String lastrefilldate) {
        this.lastrefilldate = lastrefilldate;
    }

    public String getRefill() {
        return refill.toString();
    }

    public void setRefill(Long refill) {
        this.refill = refill;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getDoctorID() {
        return doctorID.toString();
    }

    public void setDoctorID(Long doctorID) {
        this.doctorID = doctorID;
    }

    public String getDaysofsupply() {
        return daysofsupply.toString();
    }

    public void setDaysofsupply(Long daysofsupply) {
        this.daysofsupply = daysofsupply;
    }

    public String getNextrefilldate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        try {
            if(refill>0) {
                Date date = dateFormat.parse(lastrefilldate);
                Calendar c = Calendar.getInstance();
                c.setTime(date);
                c.add(Calendar.DAY_OF_MONTH, daysofsupply.intValue());
                return dateFormat.format(c.getTime());
            }
            else{
                return "Not Available";
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return "Error";
        }
    }
}
