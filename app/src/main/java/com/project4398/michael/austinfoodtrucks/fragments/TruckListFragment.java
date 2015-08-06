package com.project4398.michael.austinfoodtrucks.fragments;

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
import android.widget.ListView;

import com.project4398.michael.austinfoodtrucks.R;
import com.project4398.michael.austinfoodtrucks.AWSInterface;
import com.project4398.michael.austinfoodtrucks.TruckInfo;
import com.project4398.michael.austinfoodtrucks.TruckListAdapter2;

import java.util.ArrayList;

/**
 * Displays the list of trucks to the user.
 *
 * @author Paul M. Roberts
 * @author Luis M. Rocha
 */
public class TruckListFragment extends Fragment implements LocationListener
{
    private Context mContext;
    //TruckListAdapter mAdapter;
    private TruckListAdapter2 mAdapter2;
    private ListView mTruckList;
    public static ArrayList<TruckInfo> TLITemp;
    public TruckListFragment newFragment()
    {
        TruckListFragment fragment = new TruckListFragment();
        return fragment;
    }

    LocationManager mLocationManager;
    AWSInterface s3Interface;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mContext = getActivity();

        mLocationManager = (LocationManager)mContext.getSystemService(Context.LOCATION_SERVICE);

        Location location = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);



        s3Interface = AWSInterface.getPlayer();
        TLITemp = AWSInterface.getPlayer().mTruckList;

    }
    @Override
    public void onResume()
    {
        super.onResume();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_truck_list, container, false);
        mTruckList = (ListView) rootView.findViewById(R.id.TruckList);
        setList(TLITemp);
        mTruckList.setAdapter(mAdapter2);
        return rootView;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
    }
    public void setList(ArrayList<TruckInfo> songList)
    {
        TruckInfo[] menuArray = TLITemp.toArray(new TruckInfo[TLITemp.size()]);
        mAdapter2 = new TruckListAdapter2(mContext, menuArray);
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

    /**
     * Indicates that the location has changed. And it is called whenver the loction has been
     * changed.
     * @param location the new location..
     */
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
