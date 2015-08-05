package com.project4398.michael.austinfoodtrucks;

import android.graphics.drawable.Drawable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by PRoberts on 7/16/15.
 */
public class TruckInfo implements Serializable
{
    public String imageURL = "https://s3.amazonaws.com/aft.photos.500.500/Truck+(1).jpg";
    public Drawable image;
    public String name;
    public String about;
    public String phoneNumber;
    public String foodType;

    public float distance;
    public int id = -1;
    public boolean favorite;
    public double latitude = 0.0;
    public double longitude = 0.0;
    public ArrayList<menuItem> menu;
    private String UserID;
    private String Password;

    public TruckInfo()
    {
        menu = new ArrayList<menuItem>();
    }
    public boolean CheckCredentials(String UID, String password)
    {
        if (UserID.equals(UID) && Password.equals(password))
        {
            return true;
        }
        return false;
    }
    public void setUserID(String id)
    {
        UserID = id;
    }
    public void setPassword(String password)
    {
        Password = password;
    }
    public String getUserID(){
        return UserID;
    }
    public String getPassword(){
        return Password;
    }

    public TruckInfo(TruckInfo TI)
    {
        menu = new ArrayList<menuItem>();
        menu = TI.menu;
        imageURL = TI.imageURL;
        image = TI.image;
        name = TI.name;
        about = TI.about;
        phoneNumber = TI.phoneNumber;
        foodType = TI.foodType;
        distance = TI.distance;
        id = TI.id;
        favorite = TI.favorite;
        latitude = TI.latitude;
        longitude = TI.longitude;
        UserID = TI.UserID;
        Password = TI.Password;
    }
}
