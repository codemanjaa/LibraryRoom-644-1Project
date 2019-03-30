package com.hevs.codemanja.roomdbdemo.Database.entity;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;


@Entity(tableName = "ShelfEntity")
public class ShelfEntity implements Parcelable {

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








}
