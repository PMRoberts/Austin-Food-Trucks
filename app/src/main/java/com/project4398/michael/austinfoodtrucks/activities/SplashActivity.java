package com.project4398.michael.austinfoodtrucks.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.project4398.michael.austinfoodtrucks.AWSInterface;
import com.project4398.michael.austinfoodtrucks.R;
import com.project4398.michael.austinfoodtrucks.TruckInfo;

import java.net.InetAddress;
import java.util.ArrayList;

/**
 * Splash Screen that is display while the information from S3 is beig downloaded onto the device.
 * This includes the TruckInfo for each Item and The MenuItem for each truck. It also checks if
 * network connection is connected.
 *
 *
 * @author Paul M. Roberts
 * @author Luis M. Rocha
 */
public class SplashActivity extends Activity
{
    AWSInterface s3Interface;
    Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if(!isNetworkConnected())
        {
            Log.i("stuff", "blah blah blah");
            Toast.makeText(this, "no internet connection", Toast.LENGTH_SHORT).show();
            this.finish();
        }

        mContext = this;

        if(AWSInterface.getPlayer() == null)
        {
            AWSInterface.setPlayer(new AWSInterface(this));
        }
        s3Interface = AWSInterface.getPlayer();

//        s3Interface.DownloadList();

        new AsyncLoadList().execute();

//        Intent profileIntent = new Intent(mContext, TruckListActivity.class);
//        mContext.startActivity(profileIntent);
    }

    /**
     * Creates a sync class that is used to call download list.
     */
    private class AsyncLoadList extends AsyncTask<Void, Void, Void>
    {
//        ProgressDialog pdLoading = new ProgressDialog(mContext);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
//            pdLoading.setMessage("\tLoading...");
//            pdLoading.show();
        }
        @Override
        protected Void doInBackground(Void... params) {

            //this method will be running on background thread so don't update UI frome here
            //do your long running http tasks here,you dont want to pass argument and u can access the parent class' variable url over here

            s3Interface.DownloadList();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            //this method will be running on UI thread
//            pdLoading.dismiss();

            Intent profileIntent = new Intent(mContext, TruckListActivity.class);
            mContext.startActivity(profileIntent);
            finish();
        }

    }

    /**
     * Checks to see if the network is currently connected.
     * @return True or false depending on if network connectivity is found
     */
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni == null) {
            // There are no active networks.
            return false;
        } else
            return true;
    }

}
