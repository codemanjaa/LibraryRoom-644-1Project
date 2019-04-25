package com.hevs.codemanja.roomdbdemo.Database.entity;


import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;


public class ShelfEntity implements Parcelable {

    private String spotid;
    private String category;
    private String desc;



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

    public void  setSpotid(@NonNull String spotid) {
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

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    public ShelfEntity(Parcel in){
        spotid = in.readString();
        desc = in.readString();
        category = in.readString();
    }


    public static final Creator<ShelfEntity> CREATOR = new Creator<ShelfEntity>() {
        @Override
        public ShelfEntity createFromParcel(Parcel in) {
            return new ShelfEntity(in);
        }

        @Override
        public ShelfEntity[] newArray(int size) {
            return new ShelfEntity[size];
        }
    };

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("Category", category);
        result.put("Description", desc);


        return result;
    }








}
