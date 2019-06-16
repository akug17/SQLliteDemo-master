package com.akash.sqllitedemo.Model;

public class Details {

    private String roll;
    private String name;
    private String gender;
    private String date;
private byte[] image;

    public Details(String roll, String name, String gender, String date, byte[] image) {
        this.roll = roll;
        this.name = name;
        this.gender = gender;
        this.date = date;
        this.image = image;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


}
