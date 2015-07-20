package com.project4398.michael.austinfoodtrucks;

import java.io.Serializable;

/**
 * Created by PRoberts on 7/20/15.
 */
public class menuItem implements Serializable
{
    public String image;
    public String name;
    public String description;
    public float price;
    public boolean inStock;
    public boolean favorite;
}
