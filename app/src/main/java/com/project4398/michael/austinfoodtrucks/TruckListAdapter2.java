package com.project4398.michael.austinfoodtrucks;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.project4398.michael.austinfoodtrucks.activities.TruckProfileActivity;
import com.project4398.michael.austinfoodtrucks.activities.UserProfileActivity;

import java.text.NumberFormat;

/**
 * Created by Michael on 7/22/2015.
 */
public class TruckListAdapter2 extends ArrayAdapter<TruckInfo> implements LocationListener
{

    private Context mContext;
    private final TruckInfo[] mValues;

    LocationManager mLocationManager;
    Location location;

    public TruckListAdapter2(Context context, TruckInfo[] values)
    {
        super(context, -1, values);
        mContext = context;
        mValues = values;

        mLocationManager = (LocationManager)mContext.getSystemService(Context.LOCATION_SERVICE);

        location = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
    }

//    public void SwapArrays(menuItem[] values)
//    {
//        mValues = values;
//    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.adapter_truck_list, parent, false);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.TruckInfoImage);
        imageView.setImageResource(R.drawable.splash_icon);

        TextView textView = (TextView) rowView.findViewById(R.id.TruckInfoName);
        textView.setText(mValues[position].name);

        TextView textView2 = (TextView) rowView.findViewById(R.id.TruckInfoTypes);
        textView2.setText(mValues[position].foodType.get(0));

        TextView textView3 = (TextView) rowView.findViewById(R.id.TruckInfoDistance);
        float[] tempfloat = new float[2];
        location.distanceBetween(location.getLatitude(), location.getLongitude(), mValues[position].latitude, mValues[position].longitude, tempfloat);
        textView3.setText("" + (Math.round((tempfloat[0]*0.000621371) * 100.0) / 100.0));

        ImageView imageView2 = (ImageView) rowView.findViewById(R.id.TruckInfoFavorite);
        imageView2.setImageResource(R.drawable.splash_icon);

        if(mValues[position].favorite)
        {
            imageView2.setImageResource(R.drawable.star_on);
        }
        else
        {
            imageView2.setImageResource(R.drawable.star_off);
        }
        final TruckInfo temp = mValues[position];

        imageView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (temp != null) {
                if (AWSInterface.getPlayer().ownersTruckID == temp.id) {
                    Intent profileIntent = new Intent(mContext, UserProfileActivity.class);
                    profileIntent.putExtra("ID", temp.id);
                    mContext.startActivity(profileIntent);
                } else {
                    Intent profileIntent = new Intent(mContext, TruckProfileActivity.class);
                    profileIntent.putExtra("ID", temp.id);
                    mContext.startActivity(profileIntent);
                }
            }
        }
    });

        return rowView;
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
