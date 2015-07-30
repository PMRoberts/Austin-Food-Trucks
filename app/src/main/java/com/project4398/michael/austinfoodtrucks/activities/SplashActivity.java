package com.project4398.michael.austinfoodtrucks.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;

import com.project4398.michael.austinfoodtrucks.AWSInterface;
import com.project4398.michael.austinfoodtrucks.R;
import com.project4398.michael.austinfoodtrucks.TruckInfo;

import java.util.ArrayList;

/**
 * Created by Michael on 7/29/2015.
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

        mContext = this;

        if(AWSInterface.getPlayer() == null)
        {
            AWSInterface.setPlayer(new AWSInterface(this));
        }
        s3Interface = AWSInterface.getPlayer();
        new AsyncLoadList().execute();


    }
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
        }

    }

}
