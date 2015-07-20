package com.project4398.michael.austinfoodtrucks;

import android.location.Location;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by PRoberts on 7/16/15.
 */
public class TruckListInfo implements Serializable
{
    public String image;
    public String name;
    public String about;
    public ArrayList<String> foodType;

    public float distance;
    public int id;
    public boolean favorite;
    public Location location;
    public ArrayList<menuItem> menu;
}
