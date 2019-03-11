package com.hevs.codemanja.roomdbdemo.entity;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.view.ViewDebug;

@Entity(tableName = "shelf")
public class Shelf {

    @PrimaryKey
    @NonNull
    String spotid;
    String desc;

    @Ignore
    public Shelf(){

    }

    public Shelf(String spotid, String desc ){
        this.spotid = spotid;
        this.desc = desc;
    }

    @NonNull
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

    @Override
    public  boolean equals(Object o){
        if(o == null) return  false;
        if(o == this) return  true;
        if (!(o instanceof Shelf)) return false;

        Shelf shelf = (Shelf) o;
        return shelf.getSpotid().equals(this.getSpotid());
    }
}
