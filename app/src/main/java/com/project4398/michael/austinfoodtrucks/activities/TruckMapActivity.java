package com.project4398.michael.austinfoodtrucks.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.project4398.michael.austinfoodtrucks.AWSInterface;
import com.project4398.michael.austinfoodtrucks.R;

public class TruckMapActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
        mContext = this;
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            mMap.setMyLocationEnabled(true);

            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener()
            {
                @Override
                public boolean onMarkerClick(Marker marker)
                {
                    for(int x = 0; x < AWSInterface.getPlayer().mTruckList.size(); x++)
                    {
                        Log.i("stuff", "marker title " + marker.getTitle());
                        if(AWSInterface.getPlayer().mTruckList.get(x).name.equals(marker.getTitle()))
                        {
                            View view = View.inflate(mContext,R.layout.dialog_map_item,null);
                            TextView t = (TextView)view.findViewById(R.id.MapDialogName);
                            t.setText(AWSInterface.getPlayer().mTruckList.get(x).name);
                            TextView t2 = (TextView)view.findViewById(R.id.MapDialogTypes);
                            t2.setText(AWSInterface.getPlayer().mTruckList.get(x).foodType.get(0));
                            TextView t3 = (TextView)view.findViewById(R.id.MapDialogDistance);
                            t3.setText("hello");
                            ImageView IV = (ImageView)view.findViewById(R.id.MapDialogImage);
                            if (AWSInterface.getPlayer().mTruckList.get(x).image != null)
                            {
                                IV.setImageDrawable(AWSInterface.getPlayer().mTruckList.get(x).image);
                            }
                            else
                            {
                                IV.setImageResource(R.drawable.splash_icon);
                            }
                            final int temp = x;
                            IV.setOnClickListener(new View.OnClickListener()
                            {
                                @Override
                                public void onClick(View v)
                                {
                                    if (AWSInterface.getPlayer().ownersTruckID == AWSInterface.getPlayer().mTruckList.get(temp).id)
                                    {
                                        Intent profileIntent = new Intent(mContext, UserProfileActivity.class);
                                        profileIntent.putExtra("ID", AWSInterface.getPlayer().mTruckList.get(temp).id);
                                        mContext.startActivity(profileIntent);
                                        finish();
                                    } else
                                    {
                                        Intent profileIntent = new Intent(mContext, TruckProfileActivity.class);
                                        profileIntent.putExtra("ID", AWSInterface.getPlayer().mTruckList.get(temp).id);
                                        mContext.startActivity(profileIntent);
                                        finish();
                                    }
                                }
                            });

//                            View view2 = View.inflate(mContext,R.layout.dialog_custom_title,null);
//                            TextView t3 = (TextView)view2.findViewById(R.id.DialogCustomTitle);
//                            t3.setText(temp.name);

                            new AlertDialog.Builder(mContext)
                                    //.setTitle(temp.name)
//                                    .setCustomTitle(view2)
                                    .setView(view)
                                    .setPositiveButton("Go to Page", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which)
                                        {
                                            if (AWSInterface.getPlayer().ownersTruckID == AWSInterface.getPlayer().mTruckList.get(temp).id)
                                            {
                                                Intent profileIntent = new Intent(mContext, UserProfileActivity.class);
                                                profileIntent.putExtra("ID", AWSInterface.getPlayer().mTruckList.get(temp).id);
                                                mContext.startActivity(profileIntent);
                                                finish();
                                            } else
                                            {
                                                Intent profileIntent = new Intent(mContext, TruckProfileActivity.class);
                                                profileIntent.putExtra("ID", AWSInterface.getPlayer().mTruckList.get(temp).id);
                                                mContext.startActivity(profileIntent);
                                                finish();
                                            }
                                        }
                                    })
                                    .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            // continue with delete
                                        }
                                    })
                                    .show();
                        }
                    }
                    return false;
                }
            });
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        for(int x = 0; x < AWSInterface.getPlayer().mTruckList.size(); x++)
        {
            Drawable d;
            if(AWSInterface.getPlayer().mTruckList.get(x).image != null)
            {
                d = AWSInterface.getPlayer().mTruckList.get(x).image;
            }
            else
            {
                d = getDrawable(R.drawable.splash_icon);
            }
            Bitmap mutableBitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(mutableBitmap);
            d.setBounds(0, 0, 100, 100);
            d.draw(canvas);
            MarkerOptions mOps = new MarkerOptions().position(new LatLng(AWSInterface.getPlayer().mTruckList.get(x).latitude,
                    AWSInterface.getPlayer().mTruckList.get(x).longitude)).title(AWSInterface.getPlayer().mTruckList.get(x).name)
                    .icon(BitmapDescriptorFactory.fromBitmap(mutableBitmap));

            mMap.addMarker(mOps);
            mutableBitmap.recycle();
        }

        mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener()
        {
            @Override
            public void onMyLocationChange(Location arg0)
            {
                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(new LatLng(arg0.getLatitude(), arg0.getLongitude()))
                        .zoom(13)
                        .bearing(0)
                        .tilt(0)
                        .build();
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                mMap.setOnMyLocationChangeListener(null);
            }
        });
    }
}
