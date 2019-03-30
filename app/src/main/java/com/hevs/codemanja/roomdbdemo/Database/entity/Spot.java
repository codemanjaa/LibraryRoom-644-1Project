package com.hevs.codemanja.roomdbdemo.Database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "spot")
public class Spot {

    @PrimaryKey(autoGenerate = true)
    private int spotId;
    private String desc;
    private String category;

    public int getSpotId() {
        return spotId;
    }

    public String getDesc() {
        return desc;
    }

    public String getCategory() {
        return category;
    }

    public void setSpotId(int spotId) {
        this.spotId = spotId;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public Spot(String desc, String category) {
        this.desc = desc;
        this.category = category;
    }


}
