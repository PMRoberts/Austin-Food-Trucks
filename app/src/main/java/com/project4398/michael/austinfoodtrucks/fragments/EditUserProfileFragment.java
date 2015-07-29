package com.project4398.michael.austinfoodtrucks.fragments;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.project4398.michael.austinfoodtrucks.R;
import com.project4398.michael.austinfoodtrucks.TruckListInfo;

/**
 * Created by Michael on 7/15/2015.
 */
public class EditUserProfileFragment extends Fragment
{
    private Context mContext;
    private TruckListInfo mInfo;
    private EditText mName;
    private EditText mPhoneNumber;
    private EditText mTypes;
    private EditText mAbout;
    private ImageView mImage;

    public EditUserProfileFragment newFragment()
    {
        EditUserProfileFragment fragment = new EditUserProfileFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_user_edit_profile, container, false);

        //mImage = (ImageView)rootView.findViewById(R.id.EditTruckName);
        mName = (EditText)rootView.findViewById(R.id.EditTruckName);
        mPhoneNumber = (EditText)rootView.findViewById(R.id.EditTruckPhoneNumber);
        mTypes = (EditText)rootView.findViewById(R.id.EditTruckTypes);
        mAbout = (EditText)rootView.findViewById(R.id.EditTruckInfo);

        mName.setText(mInfo.name);
        mTypes.setText(mInfo.foodType.get(0));
        mAbout.setText(mInfo.about);
        mPhoneNumber.setText(mInfo.phoneNumber);

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
