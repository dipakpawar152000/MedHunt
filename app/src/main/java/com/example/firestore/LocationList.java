package com.example.firestore;

import com.google.firebase.firestore.Exclude;

import java.util.List;

public class LocationList {
    @Exclude
    private String id;
    int i = 0;
    public List<String> lati,longi,nam,ph;

    private String Latitide,Longitude,Name,Phone;

    public LocationList() {




    }

    public LocationList(String Latitide, String Longitude, String Name, String Phone) {

        this.Latitide = Latitide;
        this.Longitude = Longitude;
        this.Name = Name;
        this.Phone = Phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLatitide() {

        return Latitide;
    }

    public void setLatitide(String latitide) {
        this.Latitide = Latitide;
    }

    public void setLongitude(String longitude) {
        this.Longitude = Longitude;
    }

    public void setName(String name) {
        this.Name = Name;
    }

    public void setPhone(String phone) {
        this.Phone = Phone;
    }

    public String getLongitude() {
        return Longitude;
    }



    public String getName() {
        return Name;
    }



    public String getPhone() {
        return Phone;
    }


}
