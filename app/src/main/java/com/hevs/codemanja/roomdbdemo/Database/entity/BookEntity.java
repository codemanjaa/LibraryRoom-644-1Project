package com.hevs.codemanja.roomdbdemo.Database.entity;



import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;


public class BookEntity{

    private String bid;
    private String title;
    private String url;
    private String f_spotid;




    public BookEntity(){

    }



    @Exclude
    public String getBid() {
        return bid;
    }
    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getUrl(){ return url;}
    public void setImage(String url){this.url = url;}

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }



    @Exclude
    public String getF_spotid() {
        return f_spotid;
    }

    public void setF_spotid(String f_spotid) {
        this.f_spotid = f_spotid;
    }



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


    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("title", title);
        result.put("image", url);

        return result;
    }




}
