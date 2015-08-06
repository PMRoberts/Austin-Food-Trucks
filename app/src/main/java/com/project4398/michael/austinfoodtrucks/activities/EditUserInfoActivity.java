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
 * Houses edituserinfo fragments.
 *
 * @author Paul M. Roberts
 * @author Luis M. Rocha
 */
public class EditUserInfoActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();

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

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        //finish();
    }
}
