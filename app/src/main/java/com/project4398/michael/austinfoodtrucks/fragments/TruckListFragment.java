package com.project4398.michael.austinfoodtrucks.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project4398.michael.austinfoodtrucks.R;

/**
 * Created by Michael on 7/15/2015.
 */
public class TruckListFragment extends Fragment
{
    private Context mContext;
    public TruckListFragment newFragment()
    {
        TruckListFragment fragment = new TruckListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
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
        return rootView;
    }
}
