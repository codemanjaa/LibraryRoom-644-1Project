package com.hevs.codemanja.roomdbdemo.Database.entity;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


@Entity(tableName = "ShelfEntity")
public class ShelfEntity {

    @PrimaryKey
            @NonNull
    String spotid;
    String desc;
    String category;


    public ShelfEntity(){

    }

    public ShelfEntity(@NonNull String spotid, String desc, String category ){
        this.spotid = spotid;
        this.desc = desc;
        this.category = category;
    }


    public String getSpotid() {
        return spotid;
    }

    public void setSpotid(@NonNull String spotid) {
        this.spotid = spotid;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setCategory(String category){this.category = category;}

    public String getCategory(){return this.category;}

     @Override
     public  boolean equals(Object o){
     if(o == null) return  false;
     if(o == this) return  true;
     if (!(o instanceof ShelfEntity)) return false;
     ShelfEntity shelf = (ShelfEntity) o;
     return shelf.getSpotid().equals(this.getSpotid());
     }





}
