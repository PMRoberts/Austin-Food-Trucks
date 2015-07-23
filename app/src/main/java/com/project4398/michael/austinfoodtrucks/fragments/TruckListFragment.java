package com.project4398.michael.austinfoodtrucks.fragments;

import android.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.MergeCursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.project4398.michael.austinfoodtrucks.R;
import com.project4398.michael.austinfoodtrucks.TruckListAdapter;
import com.project4398.michael.austinfoodtrucks.TruckListInfo;
import com.project4398.michael.austinfoodtrucks.menuItem;

import java.util.ArrayList;

/**
 * Created by Michael on 7/15/2015.
 */
public class TruckListFragment extends Fragment
{
    private Context mContext;
    TruckListAdapter mAdapter;
    private ListView mTruckList;
    public static ArrayList<TruckListInfo> TLITemp;
    public static ArrayList<menuItem> menuTemp;
    public static ArrayList<menuItem> menuTemp2;
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
        mAdapter = new TruckListAdapter(mContext, null);

        TLITemp = new ArrayList<TruckListInfo>();
        menuTemp = new ArrayList<menuItem>();

        menuTemp.add(new menuItem());
        menuTemp.get(menuTemp.size()-1).name = "food1";
        menuTemp.get(menuTemp.size()-1).description = "it is edable";
        menuTemp.get(menuTemp.size()-1).price = 7.00f;
        menuTemp.get(menuTemp.size()-1).inStock = true;
        menuTemp.get(menuTemp.size()-1).favorite = true;

        menuTemp.add(new menuItem());
        menuTemp.get(menuTemp.size()-1).name = "food2";
        menuTemp.get(menuTemp.size()-1).description = "it is edable";
        menuTemp.get(menuTemp.size()-1).price = 7.00f;
        menuTemp.get(menuTemp.size()-1).inStock = true;
        menuTemp.get(menuTemp.size()-1).favorite = true;

        menuTemp.add(new menuItem());
        menuTemp.get(menuTemp.size()-1).name = "food3";
        menuTemp.get(menuTemp.size()-1).description = "it is edable";
        menuTemp.get(menuTemp.size()-1).price = 7.00f;
        menuTemp.get(menuTemp.size()-1).inStock = true;
        menuTemp.get(menuTemp.size()-1).favorite = true;

        menuTemp.add(new menuItem());
        menuTemp.get(menuTemp.size()-1).name = "food4";
        menuTemp.get(menuTemp.size()-1).description = "it is edable";
        menuTemp.get(menuTemp.size()-1).price = 7.00f;
        menuTemp.get(menuTemp.size()-1).inStock = true;
        menuTemp.get(menuTemp.size()-1).favorite = true;

        menuTemp.add(new menuItem());
        menuTemp.get(menuTemp.size()-1).name = "food5";
        menuTemp.get(menuTemp.size()-1).description = "it is edable";
        menuTemp.get(menuTemp.size()-1).price = 7.00f;
        menuTemp.get(menuTemp.size()-1).inStock = true;
        menuTemp.get(menuTemp.size()-1).favorite = true;

        menuTemp2 = new ArrayList<menuItem>();

        menuTemp2.add(new menuItem());
        menuTemp2.get(menuTemp2.size()-1).name = "cow";
        menuTemp2.get(menuTemp2.size()-1).description = "it is edable";
        menuTemp2.get(menuTemp2.size()-1).price = 7.00f;
        menuTemp2.get(menuTemp2.size()-1).inStock = true;
        menuTemp2.get(menuTemp2.size()-1).favorite = true;

        menuTemp2.add(new menuItem());
        menuTemp2.get(menuTemp2.size()-1).name = "chicken";
        menuTemp2.get(menuTemp2.size()-1).description = "it is edable";
        menuTemp2.get(menuTemp2.size()-1).price = 7.00f;
        menuTemp2.get(menuTemp2.size()-1).inStock = true;
        menuTemp2.get(menuTemp2.size()-1).favorite = true;

        menuTemp2.add(new menuItem());
        menuTemp2.get(menuTemp2.size()-1).name = "lamb";
        menuTemp2.get(menuTemp2.size()-1).description = "it is edable";
        menuTemp2.get(menuTemp2.size()-1).price = 7.00f;
        menuTemp2.get(menuTemp2.size()-1).inStock = true;
        menuTemp2.get(menuTemp2.size()-1).favorite = true;

        menuTemp2.add(new menuItem());
        menuTemp2.get(menuTemp2.size()-1).name = "pig";
        menuTemp2.get(menuTemp2.size()-1).description = "it is edable";
        menuTemp2.get(menuTemp2.size()-1).price = 7.00f;
        menuTemp2.get(menuTemp2.size()-1).inStock = true;
        menuTemp2.get(menuTemp2.size()-1).favorite = true;

        menuTemp2.add(new menuItem());
        menuTemp2.get(menuTemp2.size()-1).name = "fish";
        menuTemp2.get(menuTemp2.size()-1).description = "it is edable";
        menuTemp2.get(menuTemp2.size()-1).price = 7.00f;
        menuTemp2.get(menuTemp2.size()-1).inStock = true;
        menuTemp2.get(menuTemp2.size()-1).favorite = true;

        menuTemp2.add(new menuItem());
        menuTemp2.get(menuTemp2.size()-1).name = "rock";
        menuTemp2.get(menuTemp2.size()-1).description = "it is edable";
        menuTemp2.get(menuTemp2.size()-1).price = 7.00f;
        menuTemp2.get(menuTemp2.size()-1).inStock = true;
        menuTemp2.get(menuTemp2.size()-1).favorite = true;

        menuTemp2.add(new menuItem());
        menuTemp2.get(menuTemp2.size()-1).name = "plant";
        menuTemp2.get(menuTemp2.size()-1).description = "it is edable";
        menuTemp2.get(menuTemp2.size()-1).price = 7.00f;
        menuTemp2.get(menuTemp2.size()-1).inStock = true;
        menuTemp2.get(menuTemp2.size()-1).favorite = true;




        TLITemp.add(new TruckListInfo());
        TLITemp.get(TLITemp.size()-1).name = "bob";
        TLITemp.get(TLITemp.size()-1).foodType = new ArrayList<String>();
        TLITemp.get(TLITemp.size()-1).foodType.add("person");
        TLITemp.get(TLITemp.size()-1).distance = 5000.0f;
        TLITemp.get(TLITemp.size()-1).favorite = true;
        TLITemp.get(TLITemp.size()-1).id = (TLITemp.size()-1);
        TLITemp.get(TLITemp.size()-1).about = "this is where the about info would go uf i had anything to say about this.";
        TLITemp.get(TLITemp.size()-1).menu = menuTemp;


        TLITemp.add(new TruckListInfo());
        TLITemp.get(TLITemp.size()-1).name = "trucin";
        TLITemp.get(TLITemp.size()-1).foodType = new ArrayList<String>();
        TLITemp.get(TLITemp.size()-1).foodType.add("tacos");
        TLITemp.get(TLITemp.size()-1).foodType.add("tacos");
        TLITemp.get(TLITemp.size()-1).foodType.add("tacos");
        TLITemp.get(TLITemp.size()-1).distance = 1.2f;
        TLITemp.get(TLITemp.size()-1).favorite = true;
        TLITemp.get(TLITemp.size()-1).id = (TLITemp.size()-1);
        TLITemp.get(TLITemp.size()-1).about = "its so truckin good yo. itle blow yo miiiinnnnd!!!!!";
        TLITemp.get(TLITemp.size()-1).menu = menuTemp2;

        TLITemp.add(new TruckListInfo());
        TLITemp.get(TLITemp.size()-1).name = "burgs";
        TLITemp.get(TLITemp.size()-1).foodType = new ArrayList<String>();
        TLITemp.get(TLITemp.size()-1).foodType.add("burgers");
        TLITemp.get(TLITemp.size()-1).distance = 1.0f;
        TLITemp.get(TLITemp.size()-1).favorite = false;
        TLITemp.get(TLITemp.size()-1).id = (TLITemp.size()-1);
        TLITemp.get(TLITemp.size()-1).about = "i sear dead cow flesh on a burning hot mettle slab";
        TLITemp.get(TLITemp.size()-1).menu = menuTemp;

        TLITemp.add(new TruckListInfo());
        TLITemp.get(TLITemp.size()-1).name = "dfasf";
        TLITemp.get(TLITemp.size()-1).foodType = new ArrayList<String>();
        TLITemp.get(TLITemp.size()-1).foodType.add("erererf");
        TLITemp.get(TLITemp.size()-1).foodType.add("cvbcvbcvb");
        TLITemp.get(TLITemp.size()-1).distance = 5050.5f;
        TLITemp.get(TLITemp.size()-1).favorite = false;
        TLITemp.get(TLITemp.size()-1).id = (TLITemp.size()-1);
        TLITemp.get(TLITemp.size()-1).about = " hkdlfashdflk hs;dfkjsah  reiotwre cb,xmvbxzv euiwqurhysdfhaksl";
        TLITemp.get(TLITemp.size()-1).menu = menuTemp;

        TLITemp.add(new TruckListInfo());
        TLITemp.get(TLITemp.size()-1).name = "food and stuff";
        TLITemp.get(TLITemp.size()-1).foodType = new ArrayList<String>();
        TLITemp.get(TLITemp.size()-1).foodType.add("food");
        TLITemp.get(TLITemp.size()-1).foodType.add("stuff");
        TLITemp.get(TLITemp.size()-1).distance = 2.0f;
        TLITemp.get(TLITemp.size()-1).favorite = true;
        TLITemp.get(TLITemp.size()-1).id = (TLITemp.size()-1);
        TLITemp.get(TLITemp.size()-1).about = "we have food and stuff. buy stuff. or dont. we dont care. ";
        TLITemp.get(TLITemp.size()-1).menu = menuTemp2;

        TLITemp.add(new TruckListInfo());
        TLITemp.get(TLITemp.size()-1).name = "t";
        TLITemp.get(TLITemp.size()-1).foodType = new ArrayList<String>();
        TLITemp.get(TLITemp.size()-1).foodType.add("thai");
        TLITemp.get(TLITemp.size()-1).distance = 5.0f;
        TLITemp.get(TLITemp.size()-1).favorite = false;
        TLITemp.get(TLITemp.size()-1).id = (TLITemp.size()-1);
        TLITemp.get(TLITemp.size()-1).about = "thai breastaurant. get it?";
        TLITemp.get(TLITemp.size()-1).menu = menuTemp;


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
        mTruckList.setAdapter(mAdapter);
        return rootView;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
    }
    public void setList(ArrayList<TruckListInfo> songList)
    {
        String temp;
        String temp2;
        MatrixCursor matrixCursor = new MatrixCursor(new String[] {"_id","image", "name", "distance", "foodType", "favorite", "id"});
        if(songList != null)
        {
            if (songList.size() == 0)
            {
                matrixCursor.addRow(new String[]{"" + 1, null, null, null, null});
            } else {
                for (int x = 0; x < songList.size(); x++)
                {
                    temp = "";
                    temp2 = "";
                    for(int y = 0; y < songList.get(x).foodType.size(); y++)
                    {
                        temp += songList.get(x).foodType.get(y);
                        if(y != songList.get(x).foodType.size()-1)
                        {
                            temp += ", ";
                        }
                    }
                    if(songList.get(x).favorite)
                    {
                        temp2 = "true";
                    }
                    else
                    {
                        temp2 = "false";
                    }
                    Log.i("stuff", "got to creating to cursor");
                    matrixCursor.addRow(new String[]{"" + 1,
                            songList.get(x).image,
                            songList.get(x).name,
                            "" + songList.get(x).distance,
                            temp,
                            temp2,
                            "" + songList.get(x).id});
                }
            }
            Cursor TruckListCursor = new MergeCursor(new Cursor[]{matrixCursor, null});
            mAdapter.swapCursor(TruckListCursor);
        }
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
