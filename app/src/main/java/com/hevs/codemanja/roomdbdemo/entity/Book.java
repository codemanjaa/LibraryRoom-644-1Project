package com.hevs.codemanja.roomdbdemo.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;


@Entity(tableName = "books", foreignKeys = @ForeignKey(entity = Shelf.class, onDelete= ForeignKey.CASCADE,
        parentColumns = "spotid",
        childColumns = "f_spotid"
        ),
        indices = {@Index("f_spotid")}
        )

public class Book implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int bid;
    private String title;
    private String category;

    @ForeignKey(entity = Shelf.class,
            parentColumns = "spotid",
            childColumns = "f_spotid"
    )
    private String f_spotid;

    @Ignore
    private int image;

    @Ignore
    public Book(){

    }

    public Book(@NonNull int bid, String title, String category, String f_spotid) {

        this.bid = bid;
        this.title = title;
        this.category = category;
        this.f_spotid = f_spotid;

    }



    public String getF_spotid() {
        return f_spotid;
    }

    public void setF_spotid(String f_spotid) {
        this.f_spotid = f_spotid;
    }

    public Book(@NonNull int bid, String title, String category, int image) {
        this.bid = bid;
        this.title = title;
        this.category = category;

        this.image = image;
    }

    public int getBid() {
        return bid;
    }

    public int getImage(){ return image;}
    public void setImage(int image){this.image = image;}
    public void setBid(int bid) {
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





    public Book(Parcel in) {
        bid = in.readInt();
        title = in.readString();
        category = in.readString();
        f_spotid = in.readString();
       // image = in.readInt();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };


    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(bid);
        dest.writeString(title);
        dest.writeString(category);
        dest.writeString(f_spotid);
       // dest.writeInt(image);
    }


}
