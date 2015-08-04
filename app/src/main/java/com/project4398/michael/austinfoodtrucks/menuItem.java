package com.project4398.michael.austinfoodtrucks;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

/**
 * Created by PRoberts on 7/20/15.
 */
public class menuItem implements Serializable
{
    public Drawable image;
    public String name;
    public String description;
    public String price;
    public int TruckId = -1;
    public int id = -1;
    public boolean inStock = false;
    public boolean favorite = false;
}
