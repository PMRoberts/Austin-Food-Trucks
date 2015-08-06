package com.project4398.michael.austinfoodtrucks.activities;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.project4398.michael.austinfoodtrucks.AWSInterface;
import com.project4398.michael.austinfoodtrucks.R;
import com.project4398.michael.austinfoodtrucks.TruckInfo;
import com.project4398.michael.austinfoodtrucks.fragments.MenuFragment;
import com.project4398.michael.austinfoodtrucks.fragments.TruckProfileFragment;

/**
 * * Houses an instance of TruckProfilesFragments, an instance of google Maps, and an instance of
 * MenuFragment. This is what a non logged in truck owner or user will do.
 *
 * @author Paul M. Roberts
 * @author Luis M. Rocha
 */
public class TruckProfileActivity extends AppCompatActivity
{
    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    public TruckInfo mInfo;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        mInfo = AWSInterface.getPlayer().getTruckByID(intent.getIntExtra("ID", 0));
        //Bundle bundle = new Bundle();
        //bundle.putInt("ID", intent.getIntExtra("ID", 0));


//        mInfo = (TruckInfo)bundle.getSerializable("info");

        setContentView(R.layout.activity_truck_profile);


        Fragment newFragment = new TruckProfileFragment().newFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        newFragment.setArguments(bundle);
        ft.add(R.id.InfoContainer, newFragment).commit();

        setUpMapIfNeeded();


        Fragment newFragment2 = new MenuFragment().newFragment();
        FragmentTransaction ft2 = getFragmentManager().beginTransaction();
        newFragment2.setArguments(bundle);
        ft2.add(R.id.MenuContainer, newFragment2).commit();
    }


    @Override
    protected void onResume()
    {
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
            //mMap.setMyLocationEnabled(true); @todo Maybe turn this back on troubleshooting an error that keeps happening.
            //This errror
            //98.michael.austinfoodtrucks/com.project4398.michael.austinfoodtrucks.activities.TruckProfileActivity}:
            // java.lang.NullPointerException: Attempt to invoke virtual method
            // 'void com.google.android.gms.maps.GoogleMap.setMyLocationEnabled(boolean)
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

        //mMap.setMyLocationEnabled(true);
        mMap.addMarker(new MarkerOptions().position(new LatLng(mInfo.latitude, mInfo.longitude)).title("Marker"));

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(mInfo.latitude, mInfo.longitude))      // Sets the center of the map to Mountain View
                .zoom(17)                   // Sets the zoom
                .bearing(0)                // Sets the orientation of the camera to east
                .tilt(0)                   // Sets the tilt of the camera to 30 degrees
                .build();                   // Creates a CameraPosition from the builder
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        //mMap.addMarker(new MarkerOptions().position(new LatLng(mMap.getMyLocation().getLatitude(), mMap.getMyLocation().getLongitude())).title("Marker"));
        //Log.i("stuff", "" + mMap.isMyLocationEnabled());

    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this,TruckListActivity.class);
        startActivity(intent);
        finish();
    }
}
