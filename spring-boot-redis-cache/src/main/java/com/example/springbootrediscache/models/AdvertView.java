package com.example.springbootrediscache.models;

import java.io.Serializable;

public class AdvertView implements Serializable {

     private String advertId;
     private String views;

    public AdvertView(String advertId, String views) {
        this.advertId = advertId;
        this.views = views;
    }

    public String getAdvertId() {
        return advertId;
    }

    public void setAdvertId(String advertId) {
        this.advertId = advertId;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }
}
