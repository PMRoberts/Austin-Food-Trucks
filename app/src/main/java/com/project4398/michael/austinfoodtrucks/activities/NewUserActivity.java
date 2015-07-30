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

/**
 * Created by PRoberts on 7/29/15.
 */
public class NewUserActivity extends AppCompatActivity
{
    public TruckInfo info;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        info = new TruckInfo();
        Bundle bundle = new Bundle();
        bundle.putSerializable("info", info);

        setContentView(R.layout.activity_new_user);


        Fragment newFragment = new EditUserInfoFragment().newFragment();
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
