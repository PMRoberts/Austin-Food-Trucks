package com.project4398.michael.austinfoodtrucks.activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.project4398.michael.austinfoodtrucks.R;
import com.project4398.michael.austinfoodtrucks.fragments.TruckListFragment;

/**
 * Created by Michael on 7/15/2015.
 */
public class TruckProfileActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truck_list);
        Fragment newFragment = new TruckListFragment().newFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(R.id.root, newFragment).commit();
    }
}
