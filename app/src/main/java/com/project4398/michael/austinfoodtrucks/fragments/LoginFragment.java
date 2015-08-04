package com.project4398.michael.austinfoodtrucks.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
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
import android.widget.TextView;
import android.widget.Toast;

import com.project4398.michael.austinfoodtrucks.AWSInterface;
import com.project4398.michael.austinfoodtrucks.R;
import com.project4398.michael.austinfoodtrucks.TruckInfo;
import com.project4398.michael.austinfoodtrucks.activities.NewUserActivity;
import com.project4398.michael.austinfoodtrucks.activities.TruckListActivity;
import com.project4398.michael.austinfoodtrucks.activities.TruckProfileActivity;

/**
 * Created by PRoberts on 7/29/15.
 */
public class LoginFragment extends Fragment
{
    private Context mContext;
    private EditText mUserID;
    private EditText mPassword;
    private Button mLogIn;
    private Button mNewUser;

    public LoginFragment newFragment()
    {
        LoginFragment fragment = new LoginFragment();
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

    @Override @SuppressLint("NewApi")
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);

        mUserID = (EditText)rootView.findViewById(R.id.EditUserID);
        mPassword = (EditText)rootView.findViewById(R.id.EditPassword);
        mLogIn = (Button)rootView.findViewById(R.id.Login);
        mNewUser = (Button)rootView.findViewById(R.id.newUser);

        mNewUser.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                    Intent profileIntent = new Intent(mContext, NewUserActivity.class);
                    mContext.startActivity(profileIntent);
                    ((Activity)mContext).finish();
            }
        });
        mLogIn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(AWSInterface.getPlayer().CheckCredentials(mUserID.getText().toString(), mPassword.getText().toString()))
                {
                    Toast.makeText(mContext,"Login Successful", Toast.LENGTH_SHORT).show();
                    Intent profileIntent = new Intent(mContext, TruckListActivity.class);
                    mContext.startActivity(profileIntent);
                    ((Activity)mContext).finish();
                }
                else
                {
                    Toast.makeText(mContext,"Invalid Login", Toast.LENGTH_SHORT).show();
                }
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
