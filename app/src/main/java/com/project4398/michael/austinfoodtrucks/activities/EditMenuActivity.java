package com.project4398.michael.austinfoodtrucks.activities;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.project4398.michael.austinfoodtrucks.R;
import com.project4398.michael.austinfoodtrucks.fragments.EditMenuFragment;
import com.project4398.michael.austinfoodtrucks.fragments.EditUserInfoFragment;

import java.io.File;

/**
 * Houses the edit menu fragments.
 *
 * @author Paul M. Roberts
 * @author Luis M. Rocha
 */
public class EditMenuActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();

        setContentView(R.layout.activity_edit_menu);

        Log.i("stuff", "menu activity");
        Fragment newFragment = new EditMenuFragment().newFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        newFragment.setArguments(bundle);
        ft.add(R.id.EditMenuRoot, newFragment).commit();

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
