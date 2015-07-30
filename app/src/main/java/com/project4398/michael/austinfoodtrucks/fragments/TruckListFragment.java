package com.project4398.michael.austinfoodtrucks.fragments;

import android.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.MergeCursor;
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
import com.project4398.michael.austinfoodtrucks.TruckListAdapter;
import com.project4398.michael.austinfoodtrucks.TruckInfo;
import com.project4398.michael.austinfoodtrucks.TruckListAdapter2;

import java.util.ArrayList;

/**
 * Created by Michael on 7/15/2015.
 */
public class TruckListFragment extends Fragment implements LocationListener
{
    private Context mContext;
    TruckListAdapter mAdapter;
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
        mAdapter = new TruckListAdapter(mContext, null);


        mLocationManager = (LocationManager)mContext.getSystemService(Context.LOCATION_SERVICE);

        Location location = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);



        s3Interface = AWSInterface.getPlayer();
        //TLITemp = s3Interface.mTruckList;
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
//        String temp;
//        String temp2;
//        MatrixCursor matrixCursor = new MatrixCursor(new String[] {"_id","image", "name", "distance", "foodType", "favorite", "id"});
//        if(songList != null)
//        {
//            if (songList.size() == 0)
//            {
//                matrixCursor.addRow(new String[]{"" + 1, null, null, null, null, null, null});
//            } else {
//                for (int x = 0; x < songList.size(); x++)
//                {
//                    temp = "";
//                    temp2 = "";
//                    for(int y = 0; y < songList.get(x).foodType.size(); y++)
//                    {
//                        temp += songList.get(x).foodType.get(y);
//                        if(y != songList.get(x).foodType.size()-1)
//                        {
//                            temp += ", ";
//                        }
//                    }
//                    if(songList.get(x).favorite)
//                    {
//                        temp2 = "true";
//                    }
//                    else
//                    {
//                        temp2 = "false";
//                    }
//                    //Log.i("stuff", "got to creating to cursor");
//                    matrixCursor.addRow(new String[]{"" + 1,
//                            songList.get(x).image,
//                            songList.get(x).name,
//                            "" + songList.get(x).distance,
//                            temp,
//                            temp2,
//                            "" + songList.get(x).id});
//                }
//            }
//            Cursor TruckListCursor = new MergeCursor(new Cursor[]{matrixCursor, null});
//            mAdapter.swapCursor(TruckListCursor);
//        }

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
