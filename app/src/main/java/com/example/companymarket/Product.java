package com.example.companymarket;

import android.net.Uri;

public class Product {
    private String Pro_Image;
    private String Pro_name;
    private int Pro_price;
    private String Pro_status;
    private String Pro_content;


    public Product(){

    }

    public Product(Uri Pro_Image, String Pro_name, int Pro_price, String Pro_status, String Pro_content) {
        this.Pro_Image = Pro_Image.toString();
        this.Pro_name = Pro_name;
        this.Pro_price = Pro_price;
        this.Pro_status = Pro_status;
        this.Pro_content = Pro_content;
    }


    public String getPro_Image() {
        return Pro_Image;
    }

    public void setPro_Image(String pro_Image) {
        Pro_Image = pro_Image;
    }

    public String getPro_name() {
        return Pro_name;
    }

    public void setPro_name(String pro_name) {
        Pro_name = pro_name;
    }

    public int getPro_price() {
        return Pro_price;
    }

    public void setPro_price(int pro_price) {
        Pro_price = pro_price;
    }

    public String getPro_status() {
        return Pro_status;
    }

    public void setPro_status(String pro_status) {
        Pro_status = pro_status;
    }

    public String getPro_content() {
        return Pro_content;
    }

    public void setPro_content(String pro_content) {
        Pro_content = pro_content;
    }

    @Override
    public String toString() {
        return "Product{" +
                "Pro_Image='" + Pro_Image + '\'' +
                ", Pro_name='" + Pro_name + '\'' +
                ", Pro_price=" + Pro_price +
                ", Pro_status='" + Pro_status + '\'' +
                ", Pro_content='" + Pro_content + '\'' +
                '}';
    }
}
