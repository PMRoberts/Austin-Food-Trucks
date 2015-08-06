package com.project4398.michael.austinfoodtrucks.fragments;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.project4398.michael.austinfoodtrucks.AWSInterface;
import com.project4398.michael.austinfoodtrucks.ExpandableHeightGridView;
import com.project4398.michael.austinfoodtrucks.MenuAdapter;
import com.project4398.michael.austinfoodtrucks.R;
import com.project4398.michael.austinfoodtrucks.menuItem;

import java.util.ArrayList;

/**
 * This is what houses the list for menu items.
 *
 * @author Paul M. Roberts
 * @author Luis M. Rocha
 */
public class MenuFragment extends Fragment
{
    private Context mContext;
    private ArrayList<menuItem> mMenu;
    private MenuAdapter mAdapter;


    /**
     * Standard constructor for MenuFragment
     * @return new MenuFragment
     */
    public MenuFragment newFragment()
    {
        MenuFragment fragment = new MenuFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mMenu = new ArrayList<menuItem>();
        mMenu = AWSInterface.getPlayer().getTruckByID(getArguments().getInt("ID", 0)).menu;
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
        if(mMenu != null)
        {
            menuItem[] menuArray = mMenu.toArray(new menuItem[mMenu.size()]);
            mAdapter = new MenuAdapter(mContext, menuArray);
            theGrid.setExpanded(true);
            theGrid.setAdapter(mAdapter);
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

}
