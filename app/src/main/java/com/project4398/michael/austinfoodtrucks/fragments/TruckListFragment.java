package com.project4398.michael.austinfoodtrucks.fragments;

import android.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.MergeCursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.project4398.michael.austinfoodtrucks.R;
import com.project4398.michael.austinfoodtrucks.TruckListAdapter;
import com.project4398.michael.austinfoodtrucks.TruckListInfo;

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

        TLITemp.add(new TruckListInfo());
        TLITemp.get(TLITemp.size()-1).name = "bob";
        TLITemp.get(TLITemp.size()-1).foodType = "person";
        TLITemp.get(TLITemp.size()-1).distance = "5000";
        TLITemp.get(TLITemp.size()-1).favorite = "true";
        TLITemp.get(TLITemp.size()-1).id = "" + (TLITemp.size()-1);

        TLITemp.add(new TruckListInfo());
        TLITemp.get(TLITemp.size()-1).name = "trucin";
        TLITemp.get(TLITemp.size()-1).foodType = "tacos";
        TLITemp.get(TLITemp.size()-1).distance = "2.7";
        TLITemp.get(TLITemp.size()-1).favorite = "false";
        TLITemp.get(TLITemp.size()-1).id = "" + (TLITemp.size()-1);

        TLITemp.add(new TruckListInfo());
        TLITemp.get(TLITemp.size()-1).name = "burgs";
        TLITemp.get(TLITemp.size()-1).foodType = "burgers";
        TLITemp.get(TLITemp.size()-1).distance = "1";
        TLITemp.get(TLITemp.size()-1).favorite = "true";
        TLITemp.get(TLITemp.size()-1).id = "" + (TLITemp.size()-1);

        TLITemp.add(new TruckListInfo());
        TLITemp.get(TLITemp.size()-1).name = "dfasf";
        TLITemp.get(TLITemp.size()-1).foodType = "person";
        TLITemp.get(TLITemp.size()-1).distance = "5000";
        TLITemp.get(TLITemp.size()-1).favorite = "g";
        TLITemp.get(TLITemp.size()-1).id = "" + (TLITemp.size()-1);

        TLITemp.add(new TruckListInfo());
        TLITemp.get(TLITemp.size()-1).name = "food and stuff";
        TLITemp.get(TLITemp.size()-1).foodType = "food";
        TLITemp.get(TLITemp.size()-1).distance = "2";
        TLITemp.get(TLITemp.size()-1).favorite = "true";
        TLITemp.get(TLITemp.size()-1).id = "" + (TLITemp.size()-1);

        TLITemp.add(new TruckListInfo());
        TLITemp.get(TLITemp.size()-1).name = "t";
        TLITemp.get(TLITemp.size()-1).foodType = "thai";
        TLITemp.get(TLITemp.size()-1).distance = "5";
        TLITemp.get(TLITemp.size()-1).favorite = "false";
        TLITemp.get(TLITemp.size()-1).id = "" + (TLITemp.size()-1);


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
        MatrixCursor matrixCursor = new MatrixCursor(new String[] {"_id","image", "name", "distance", "foodType", "favorite", "id"});
        if(songList != null)
        {
            if (songList.size() == 0)
            {
                matrixCursor.addRow(new String[]{"" + 1, null, null, null, null});
            } else {
                for (int x = 0; x < songList.size(); x++)
                {
                    Log.i("stuff", "got to creating to cursor");
                    matrixCursor.addRow(new String[]{"" + 1,
                            songList.get(x).image,
                            songList.get(x).name,
                            songList.get(x).distance,
                            songList.get(x).foodType,
                            songList.get(x).favorite,
                            songList.get(x).id});
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
