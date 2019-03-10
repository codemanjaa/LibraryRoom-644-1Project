package com.hevs.codemanja.roomdbdemo.entity;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.view.ViewDebug;

@Entity
public class Shelf {

    @PrimaryKey
    @NonNull
    String spotId;
    String desc;

    @Ignore
    public Shelf(){

    }

    public Shelf(String spotId, String desc ){
        this.spotId = spotId;
        this.desc = desc;
    }

    @NonNull
    public String getSpotId() {
        return spotId;
    }

    public void setSpotId(@NonNull String spotId) {
        this.spotId = spotId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public  boolean equals(Object o){
        if(o == null) return  false;
        if(o == this) return  true;
        if (!(o instanceof Shelf)) return false;

        Shelf shelf = (Shelf) o;
        return shelf.getSpotId().equals(this.getSpotId());
    }
}
