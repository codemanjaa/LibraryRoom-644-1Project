package com.hevs.codemanja.roomdbdemo.Database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;




@Entity(tableName = "BookEntity",
        foreignKeys =
        @ForeignKey(
                entity = ShelfEntity.class,
        parentColumns = "spotid",
        childColumns = "f_spotid",
        onDelete = ForeignKey.CASCADE
),
        indices = {
        @Index(
                value = {"f_spotid"}
)}
)
public class BookEntity implements Parcelable{

    @PrimaryKey(autoGenerate = true)
    private int bid;
    private String title;
    private String category;
    private String f_spotid;



    @Ignore
    private int image;


    public BookEntity(){

    }

    public BookEntity( String title, String category, String f_spotid) {

        this.title = title;
        this.category = category;
        this.f_spotid = f_spotid;

    }




    public int getBid() {
        return bid;
    }
    public void setBid(int bid) {
        this.bid = bid;
    }

    public int getImage(){ return image;}
    public void setImage(int image){this.image = image;}


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

    public String getF_spotid() {
        return f_spotid;
    }

    public void setF_spotid(String f_spotid) {
        this.f_spotid = f_spotid;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof BookEntity)) return false;
        BookEntity o = (BookEntity) obj;
        return o.getBid()==(this.getBid());
    }

    @Override
    public String toString() {
        return title;
    }



    public BookEntity(Parcel in) {
      //  bid = in.readLong();
        title = in.readString();
        category = in.readString();
        f_spotid = in.readString();
        // image = in.readInt();
    }

    public static final Parcelable.Creator<BookEntity> CREATOR = new Parcelable.Creator<BookEntity>() {
        @Override
        public BookEntity createFromParcel(Parcel in) {
            return new BookEntity(in);
        }

        @Override
        public BookEntity[] newArray(int size) {
            return new BookEntity[size];
        }
    };


    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(bid);
        dest.writeString(title);
        dest.writeString(category);
        dest.writeString(f_spotid);
        // dest.writeInt(image);
    }


}
