package com.project4398.michael.austinfoodtrucks.activities;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.project4398.michael.austinfoodtrucks.R;
import com.project4398.michael.austinfoodtrucks.TruckInfo;
import com.project4398.michael.austinfoodtrucks.fragments.LoginFragment;
import com.project4398.michael.austinfoodtrucks.fragments.MenuFragment;
import com.project4398.michael.austinfoodtrucks.fragments.TruckProfileFragment;

/**
 * Houses log in fragments.
 *
 * @author Paul M. Roberts
 * @author Luis M. Rocha
 */
public class LoginActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        Fragment newFragment = new LoginFragment().newFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(R.id.loginRoot, newFragment).commit();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
    }
}
