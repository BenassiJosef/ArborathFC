package com.example.josefbenassi.abroathfanzine.firebase;

/**
 * Created by josefbenassi on 06/04/2017.
 */

public class UserInformation {

    public String firstname;
    public String secondname;
    public String address;
    public String age;


    public UserInformation(){


    }

    public UserInformation(String firstname, String secondname, String address , String age) {

        this.firstname = firstname;
        this.secondname = secondname;
        this.address = address;
        this.age = age;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }




}
