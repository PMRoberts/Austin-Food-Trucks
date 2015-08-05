package com.project4398.michael.austinfoodtrucks.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.project4398.michael.austinfoodtrucks.AWSInterface;
import com.project4398.michael.austinfoodtrucks.R;
import com.project4398.michael.austinfoodtrucks.TruckInfo;
import com.project4398.michael.austinfoodtrucks.activities.EditUserInfoActivity;
import com.project4398.michael.austinfoodtrucks.activities.NewUserActivity;
import com.project4398.michael.austinfoodtrucks.activities.TruckListActivity;

/**
 * Created by PRoberts on 7/29/15.
 */
public class NewUserFragment extends Fragment
{
    private Context mContext;
    private EditText mUserID;
    private EditText mPassword;
    private EditText mRePassword;
    private Button CreateAccount;
    private Button mLogIn;
    private Button mNewUser;
    private TruckInfo mInfo;

    public NewUserFragment newFragment()
    {
        NewUserFragment fragment = new NewUserFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_new_user, container, false);

        mUserID = (EditText)rootView.findViewById(R.id.EditUserID);
        mPassword = (EditText)rootView.findViewById(R.id.EditPassword);
        mRePassword = (EditText)rootView.findViewById(R.id.EditRePassword);
        CreateAccount = (Button)rootView.findViewById(R.id.CreatAccount);

        CreateAccount.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                    if (mPassword.getText().toString().equals(mRePassword.getText().toString()) )
                    {
                        mInfo = new TruckInfo();
                        mInfo.setUserID(mUserID.getText().toString());
                        mInfo.setPassword(mPassword.getText().toString());
                        AWSInterface.getPlayer().EditTruckByID(mInfo);

                        Intent profileIntent = new Intent(mContext, EditUserInfoActivity.class);
                        Log.i("stuff", "newTRuckID = " + mInfo.id);
                        profileIntent.putExtra("ID", mInfo.id);
                        mContext.startActivity(profileIntent);
                        ((Activity)mContext).finish();
                    }
            }
        });



//        mNewUser.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                    Intent profileIntent = new Intent(mContext, NewUserActivity.class);
//                    mContext.startActivity(profileIntent);
//                    //finish();
//            }
//        });
//        mLogIn.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                if(AWSInterface.getPlayer().CheckCredentials(mUserID.getText().toString(), mPassword.getText().toString()))
//                {
//                    Toast.makeText(mContext,"Login Successful", Toast.LENGTH_SHORT).show();
//                    Intent profileIntent = new Intent(mContext, TruckListActivity.class);
//                    mContext.startActivity(profileIntent);
//                    //finish();
//                }
//                else
//                {
//                    Toast.makeText(mContext,"Invalid Login", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });



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
