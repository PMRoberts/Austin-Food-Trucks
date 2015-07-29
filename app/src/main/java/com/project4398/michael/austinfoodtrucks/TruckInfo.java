package com.project4398.michael.austinfoodtrucks;

import android.location.Location;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by PRoberts on 7/16/15.
 */
public class TruckInfo implements Serializable
{
    public String image;
    public String name;
    public String about;
    public String phoneNumber;
    public ArrayList<String> foodType;

    public float distance;
    public int id;
    public boolean favorite;
    public double latitude = 0.0;
    public double longitude = 0.0;
    public ArrayList<menuItem> menu;
}
