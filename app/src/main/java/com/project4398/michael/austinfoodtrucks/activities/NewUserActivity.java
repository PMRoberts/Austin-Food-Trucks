package com.project4398.michael.austinfoodtrucks.activities;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.GoogleMap;
import com.project4398.michael.austinfoodtrucks.R;
import com.project4398.michael.austinfoodtrucks.TruckInfo;
import com.project4398.michael.austinfoodtrucks.fragments.EditUserInfoFragment;
import com.project4398.michael.austinfoodtrucks.fragments.NewUserFragment;

/**
 * Created by PRoberts on 7/29/15.
 */
public class NewUserActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_new_user);


        Fragment newFragment = new NewUserFragment().newFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        //newFragment.setArguments(bundle);
        ft.add(R.id.newUserRoot, newFragment).commit();

    }

    @Override
    protected void onResume()
    {
        super.onResume();
    }
}
