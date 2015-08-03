package com.project4398.michael.austinfoodtrucks.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.project4398.michael.austinfoodtrucks.AWSInterface;
import com.project4398.michael.austinfoodtrucks.R;
import com.project4398.michael.austinfoodtrucks.fragments.TruckListFragment;
import com.project4398.michael.austinfoodtrucks.fragments.TruckProfileFragment;


public class TruckListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truck_list);
        Fragment newFragment = new TruckListFragment().newFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(R.id.root, newFragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_truck_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        switch (id)
        {
            case R.id.action_Login:
                Intent profileIntent = new Intent(this, LoginActivity.class);
                this.startActivity(profileIntent);
                //finish();
                return true;
            case R.id.action_Logout:
                if(AWSInterface.getPlayer().ownersTruckID >= 0)
                {
                    AWSInterface.getPlayer().ownersTruckID = -1;
                    Toast.makeText(this,"You Have Successfully Logged Out", Toast.LENGTH_SHORT).show();
                }
                //finish();
                return true;
            case R.id.action_ShowMap:
                Intent profileIntent2 = new Intent(this, TruckMapActivity.class);
                this.startActivity(profileIntent2);
                //finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private Boolean exit = false;
    @Override
    public void onBackPressed() {
        if (exit) {
            finish(); // finish activity
        } else {
            Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);

        }

    }
}
