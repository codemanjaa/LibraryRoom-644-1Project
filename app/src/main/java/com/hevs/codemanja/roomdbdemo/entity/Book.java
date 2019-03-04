package com.hevs.codemanja.roomdbdemo.entity;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Book {

    @PrimaryKey
    @NonNull
    private String bid;
    private String title;
    private String category;
    private int image;


    public Book(){

    }

    public Book(@NonNull String bid, String title, String category, int image) {
        this.bid = bid;
        this.title = title;
        this.category = category;
        this.image = image;
    }

    public String getBid() {
        return bid;
    }

    public int getImage(){ return image;}
    public void setImage(int image){this.image = image;}
    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
