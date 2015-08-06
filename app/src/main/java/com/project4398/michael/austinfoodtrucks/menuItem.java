package com.project4398.michael.austinfoodtrucks;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

/**
 * This will be the data structure for each menuItem that will be assigned to a truck info data
 * structure. This class is also serializable.
 *
 * @author Paul M. Roberts
 * @author Luis M. Rocha
 */
public class menuItem implements Serializable
{
    public Drawable image;
    public String imageUrl = "https://s3.amazonaws.com/aft.photos.250.250/5.jpg";
    public String name;
    public String description;
    public String price;
    public int TruckId = -1;
    public int id = -1;
    public boolean inStock = false;
    public boolean favorite = false;
}
