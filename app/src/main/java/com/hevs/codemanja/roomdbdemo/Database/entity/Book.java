package com.hevs.codemanja.roomdbdemo.Database.entity;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;


public class Book {

    private String id;
    private String title;
    private String category;
    private String spot;


    public Book(){

    }

@Exclude
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Exclude
    public String getSpot() {
        return spot;
    }

    public void setSpot(String spot) {
        this.spot = spot;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof Book)) return false;
        Book book = (Book) obj;

        return book.getId().equals(this.getId());
    }

    public String toString() {return title;}


    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("title", title);
        result.put("category", category);
        return result;
    }



}
