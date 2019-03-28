package com.hevs.codemanja.roomdbdemo.Database.entity;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


@Entity(tableName = "shelfEntity", primaryKeys = {"spotid"})
public class ShelfEntity {

    @NonNull
    String spotid;
    String desc;
    String category;

    @Ignore
    public ShelfEntity(){

    }

    public ShelfEntity(String spotid, String desc, String category ){
        this.spotid = spotid;
        this.desc = desc;
        this.category = category;
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

    public void setCategory(String category){this.category = category;}

    public String getCategory(){return this.category;}

    /**
    @Override
    public  boolean equals(Object o){
        if(o == null) return  false;
        if(o == this) return  true;
        if (!(o instanceof com.hevs.codemanja.roomdbdemo.Database.entity.ShelfEntity)) return false;

        com.hevs.codemanja.roomdbdemo.Database.entity.ShelfEntity shelf = (com.hevs.codemanja.roomdbdemo.Database.entity.ShelfEntity) o;
        return shelf.getSpotid().equals(this.getSpotid());
    }**/
}
