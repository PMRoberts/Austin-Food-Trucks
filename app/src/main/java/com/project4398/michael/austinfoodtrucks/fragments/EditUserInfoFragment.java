package com.project4398.michael.austinfoodtrucks.fragments;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.project4398.michael.austinfoodtrucks.AWSInterface;
import com.project4398.michael.austinfoodtrucks.R;
import com.project4398.michael.austinfoodtrucks.TruckInfo;
import com.project4398.michael.austinfoodtrucks.activities.UserProfileActivity;

import java.util.ArrayList;

/**
 * Created by Michael on 7/15/2015.
 */
public class EditUserInfoFragment extends Fragment
{
    private Context mContext;
    private TruckInfo mInfo;
    private EditText mName;
    private EditText mPhoneNumber;
    private EditText mTypes;
    private EditText mAbout;
    private ImageView mImage;
    private Button mSave;

    public EditUserInfoFragment newFragment()
    {
        EditUserInfoFragment fragment = new EditUserInfoFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {
            mInfo = AWSInterface.getPlayer().getTruckByID(getArguments().getInt("ID"));
        }

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
        View rootView = inflater.inflate(R.layout.fragment_edit_user_info, container, false);

        mImage = (ImageView)rootView.findViewById(R.id.EditTruckImage);
        mName = (EditText)rootView.findViewById(R.id.EditTruckName);
        mPhoneNumber = (EditText)rootView.findViewById(R.id.EditTruckPhoneNumber);
        mTypes = (EditText)rootView.findViewById(R.id.EditTruckTypes);
        mAbout = (EditText)rootView.findViewById(R.id.EditTruckInfo);
        mSave = (Button)rootView.findViewById(R.id.SaveShit);

        if(mInfo != null)
        {
            mName.setText(mInfo.name);
            if (!mInfo.foodType.isEmpty())
            {
                mTypes.setText(mInfo.foodType.get(0));
            }
            mAbout.setText(mInfo.about);
            mPhoneNumber.setText(mInfo.phoneNumber);

            mImage.setImageResource(R.drawable.splash_icon);
        }

        mSave.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (mInfo == null)
                {
                    mInfo = new TruckInfo();
                }
                mInfo.name = mName.getText().toString();
                mInfo.phoneNumber = mPhoneNumber.getText().toString();
                mInfo.foodType.add(mTypes.getText().toString());
                mInfo.about = mAbout.getText().toString();

                AWSInterface.getPlayer().EditTruckByID(mInfo);

                Intent profileIntent = new Intent(mContext, UserProfileActivity.class);
                profileIntent.putExtra("ID", mInfo.id);
                mContext.startActivity(profileIntent);
                //finish();
            }
        });

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
