package com.example.healthyliving;

public class Data_Coupon {
    public String key;
    public long BIN;
    public String Group;
    public String MemberID;
    public String PCN;
    public String Promo_Offer;

    public Data_Coupon(){

    }

    public String getPCN() {
        return PCN;
    }

    public void setPCN(String PCN) {
        this.PCN = PCN;
    }

    public String getGroup() {
        return Group;
    }

    public void setGroup(String group) {
        Group = group;
    }

    public long getBIN() {
        return BIN;
    }

    public void setBIN(long BIN) {
        this.BIN = BIN;
    }

    public String getMemberID() {
        return MemberID;
    }

    public void setMemberID(String member_ID) {
        MemberID = member_ID;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPromo_Offer() {
        return Promo_Offer;
    }

    public void setPromo_Offer(String promo_Offer) {
        Promo_Offer = promo_Offer;
    }
}
