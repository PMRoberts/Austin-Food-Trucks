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
 * Created by Michael on 7/15/2015.
 */
public class EditUserInfoActivity extends AppCompatActivity
{
    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    public TruckInfo info;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();

        info = (TruckInfo)bundle.getSerializable("info");

        setContentView(R.layout.activity_edit_user_info);


        Fragment newFragment = new EditUserInfoFragment().newFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        newFragment.setArguments(bundle);
        ft.add(R.id.editRoot, newFragment).commit();

    }

    @Override
    protected void onResume()
    {
        super.onResume();
    }
}
