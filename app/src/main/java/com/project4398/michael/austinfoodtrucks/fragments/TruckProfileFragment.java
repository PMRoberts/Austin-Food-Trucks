package com.project4398.michael.austinfoodtrucks.fragments;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.project4398.michael.austinfoodtrucks.R;
import com.project4398.michael.austinfoodtrucks.TruckListInfo;

/**
 * Created by Michael on 7/15/2015.
 */
public class TruckProfileFragment extends Fragment
{
    private Context mContext;
    private TruckListInfo mInfo;
    private TextView mName;
    private TextView mTypes;
    private TextView mAbout;
    private ImageView mImage;

    public TruckProfileFragment newFragment()
    {
        TruckProfileFragment fragment = new TruckProfileFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mInfo = (TruckListInfo)getArguments().getSerializable("info");

        mContext = getActivity();
    }
    @Override
    public void onResume()
    {
        super.onResume();
    }

    @Override @SuppressLint("NewApi")
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_truck_profile, container, false);

        mImage = (ImageView)rootView.findViewById(R.id.TruckImage);
        mName = (TextView)rootView.findViewById(R.id.TruckName);
        mAbout = (TextView)rootView.findViewById(R.id.TruckAbout);
        mTypes = (TextView)rootView.findViewById(R.id.TruckTypes);

        mName.setText(mInfo.name);
        mTypes.setText(mInfo.foodType);
        mAbout.setText(mInfo.about);

        mImage.setImageResource(R.drawable.soundcloud_default);

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
}
