package com.example.poojanaik.buildtech;

public class property {
    private String name;
    private String loc;
    private int area;
    private String price;
    private String bhk,contact;

    public property(String name,String contact, String loc,String bhk, String price) {
        this.name = name;
        this.contact= contact;
        this.loc= loc;
        this.area= area;
        this.bhk= bhk;
        this.price= price;
    }

    public String getname() {

        return name;
    }
    public String getContact() {
        return contact;
    }

    public String getloc() {
        return loc;
    }

    public int getarea() {
        return area;
    }

    public String getBhk() {
        return bhk;
    }

    public String getPrice() {
        return price;
    }

}
