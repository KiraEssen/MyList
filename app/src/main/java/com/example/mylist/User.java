package com.example.mylist;

import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private String dateOfBirth;
    private String email;
    private int myURL;

    public User(String name, String dateOfBirth, String email, int myURL) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.myURL = myURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMyURL() {
        return myURL;
    }

    public void setMyURL(int myURL) {
        this.myURL = myURL;
    }
}


