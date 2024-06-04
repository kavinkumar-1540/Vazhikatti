package com.example.vazhikatti;

public class User {

    private String phone;
    private String email;
    private String password;
    private  String message;



    public User( String phone, String email, String password) {
        this.phone = phone;
        this.email = email;
        this.password = password;
    }
    public User(String phone,  String password) {
        this.phone = phone;
        this.password = password;
    }

    // Getters and Setters for all fields
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
