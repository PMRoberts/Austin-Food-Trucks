package com.project4398.michael.austinfoodtrucks.fragments;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.project4398.michael.austinfoodtrucks.R;

import java.util.ArrayList;

/**
 * Created by PRoberts on 7/20/15.
 */
public class MenuFragment extends Fragment
{
    private Context mContext;
    private ArrayList<MenuItem> mMenu;

    public MenuFragment newFragment()
    {
        MenuFragment fragment = new MenuFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mMenu = new ArrayList<MenuItem>();
        mMenu = (ArrayList<MenuItem>)getArguments().getSerializable("menu");
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

        for(int x = 0; x < mMenu.size(); x++)
        {
            TextView tv1 = new TextView(mContext);
            tv1.setText("an item");
            RelativeLayout.LayoutParams relativeParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            relativeParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            tv1.setId(tv1.generateViewId());
            root.addView(tv1, relativeParams);
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
