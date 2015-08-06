package com.project4398.michael.austinfoodtrucks;

import android.graphics.drawable.Drawable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Data structure for trucks. This class is the one that will be uploaded and downloaded per each
 * truck profile. TruckInfo can also hold MenuItems
 *
 * @see  menuItem
 * @author Paul M. Roberts
 * @author Luis M. Rocha
 */
public class TruckInfo implements Serializable
{    public String imageURL = "https://s3.amazonaws.com/aft.photos.500.500/Truck+(1).jpg";
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


    /**
     * A standard constructor and initialized the menu array so that it might receive and add items
     * to it.
     */
    public TruckInfo()
    {
        menu = new ArrayList<menuItem>();
    }

    /**
     * Checks to see if the UserID and Password received math the ones on the requested profile/
     * TruckInfo data structure.
     * @param UID the entered userdID to tbe checked.
     * @param password the entered password to be matched.
     * @return returns wheater or not the credentials match up.
     */
    public boolean CheckCredentials(String UID, String password)
    {
        if (UserID.equals(UID) && Password.equals(password))
        {
            return true;
        }
        return false;
    }

    /**
     * Sets the userID for the requested Truck info.
     * @param id the new userId to be attached to the truck info.
     */
    public void setUserID(String id)
    {
        UserID = id;
    }

    /**
     * Sets the new password to be attached to the truckInfo.
     * @param password the password to be set for the truckInfo
     */
    public void setPassword(String password)
    {
        Password = password;
    }

    /**
     * Gets to the userId to be returned to the invoking object.
     * @return the userID
     */
    public String getUserID(){
        return UserID;
    }

    /**
     * Gets the passwords from the attached profile
     * @return the password
     */
    public String getPassword(){
        return Password;
    }

    /**
     * Copy constructor that copies all of it's attributes.
     * @param TI the TruckInfo to copy from.
     */
    public TruckInfo(TruckInfo TI)
    {
        menu = new ArrayList<menuItem>();
//        if(TI.menu != null)
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
