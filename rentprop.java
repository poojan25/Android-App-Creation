package com.example.poojanaik.buildtech;

public class rentprop {
    private String name;
    private String loc;
    private String price;
    private String bhk;


    public rentprop( String name, String loc, String bhk,String price) {
        this.name = name;
        this.loc= loc;
        this.bhk = bhk;
        this.price = price;
    }

    public String getname() {
        return name;
    }

    public String getloc() {
        return loc;
    }
    public String getbhk() {
        return bhk;
    }

    public String price() {
        return price;
    }


}
