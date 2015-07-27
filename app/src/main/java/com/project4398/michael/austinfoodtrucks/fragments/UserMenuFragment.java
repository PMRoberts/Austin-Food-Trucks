package com.project4398.michael.austinfoodtrucks.fragments;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.project4398.michael.austinfoodtrucks.ExpandableHeightGridView;
import com.project4398.michael.austinfoodtrucks.MenuAdapter;
import com.project4398.michael.austinfoodtrucks.R;
import com.project4398.michael.austinfoodtrucks.menuItem;

import java.util.ArrayList;

/**
 * Created by PRoberts on 7/20/15.
 */
public class UserMenuFragment extends Fragment
{
    private Context mContext;
    private ArrayList<menuItem> mMenu;
    private MenuAdapter mAdapter;


    public UserMenuFragment newFragment()
    {
        UserMenuFragment fragment = new UserMenuFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mMenu = new ArrayList<menuItem>();
        mMenu = (ArrayList<menuItem>)getArguments().getSerializable("menu");
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
        View rootView = inflater.inflate(R.layout.fragment_menu, container, false);
        RelativeLayout root = (RelativeLayout)rootView.findViewById(R.id.menuRoot);
        ExpandableHeightGridView theGrid = (ExpandableHeightGridView)rootView.findViewById(R.id.customGrid);
        menuItem[] menuArray = mMenu.toArray(new menuItem[mMenu.size()]);
        mAdapter = new MenuAdapter(mContext, menuArray);
        theGrid.setExpanded(true);
        theGrid.setAdapter(mAdapter);

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
