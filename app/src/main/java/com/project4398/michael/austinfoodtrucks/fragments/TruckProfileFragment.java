package com.project4398.michael.austinfoodtrucks.fragments;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.project4398.michael.austinfoodtrucks.AWSInterface;
import com.project4398.michael.austinfoodtrucks.R;
import com.project4398.michael.austinfoodtrucks.TruckInfo;

/**
 * Created by Michael on 7/15/2015.
 */
public class TruckProfileFragment extends Fragment implements LocationListener
{
    private Context mContext;
    private TruckInfo mInfo;
    private TextView mName;
    private TextView mTypes;
    private TextView mAbout;
    private TextView mPhoneNumber;
    private TextView mDistance;
    private ImageView mImage;
    LocationManager mLocationManager;
    Location location;

    public TruckProfileFragment newFragment()
    {
        TruckProfileFragment fragment = new TruckProfileFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //mInfo = (TruckInfo)getArguments().getSerializable("info");
        mInfo = AWSInterface.getPlayer().getTruckByID(getArguments().getInt("ID", 0));

        mContext = getActivity();

        mLocationManager = (LocationManager)mContext.getSystemService(Context.LOCATION_SERVICE);
        location = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
    }
    @Override
    public void onResume()
    {
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_truck_profile, container, false);

        mImage = (ImageView)rootView.findViewById(R.id.TruckImage);
        mName = (TextView)rootView.findViewById(R.id.TruckName);
        mAbout = (TextView)rootView.findViewById(R.id.TruckAbout);
        mTypes = (TextView)rootView.findViewById(R.id.TruckTypes);
        mPhoneNumber = (TextView)rootView.findViewById(R.id.TruckPhoneNumber);

        TextView textView3 = (TextView)rootView.findViewById(R.id.Distance);
        float[] tempfloat = new float[2];
        location.distanceBetween(location.getLatitude(), location.getLongitude(), mInfo.latitude, mInfo.longitude, tempfloat);
        textView3.setText("" + (Math.round((tempfloat[0] * 0.000621371) * 100.0) / 100.0)+ "MI");

        mName.setText(mInfo.name);
        if(!mInfo.foodType.isEmpty()) {
            mTypes.setText(mInfo.foodType.get(0));
        }
        mAbout.setText(mInfo.about);
        mPhoneNumber.setText(mInfo.phoneNumber);

        if(mInfo.image != null)
        {
            mImage.setImageDrawable(mInfo.image);
        }
        else
        {
            mImage.setImageResource(R.drawable.splash_icon);
        }

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public void onPause()
    {
        super.onPause();
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
    }

    public void onLocationChanged(Location location) {
        if (location != null) {
            Log.v("Location Changed", location.getLatitude() + " and " + location.getLongitude());
            mLocationManager.removeUpdates(this);
        }
    }

    // Required functions
    public void onProviderDisabled(String arg0) {}
    public void onProviderEnabled(String arg0) {}
    public void onStatusChanged(String arg0, int arg1, Bundle arg2) {}
}
