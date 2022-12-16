package com.example.healthyliving;

public class Data_Customer {
    private String Name;
    private String Contact;
    private String Email;
    private String Username;
    private String Password;

    public Data_Customer() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
    public Data_Customer LoginData(){
        Data_Customer cust=new Data_Customer();
        cust.Username=getUsername();
        cust.Password=getPassword();
        cust.Email=getEmail();
        return cust;
    }
    public Data_Customer Signupdata(){
        Data_Customer cust=new Data_Customer();
        cust.Name=getName();
        cust.Contact=getContact();
        cust.Username=getUsername();
        cust.Email=getEmail();
        return cust;
    }
}
