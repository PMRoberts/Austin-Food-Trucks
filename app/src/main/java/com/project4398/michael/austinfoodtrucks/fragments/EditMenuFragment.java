package com.project4398.michael.austinfoodtrucks.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
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
import com.project4398.michael.austinfoodtrucks.menuItem;

import java.io.FileNotFoundException;

/**
 * Created by Michael on 8/2/2015.
 */
public class EditMenuFragment extends Fragment
{
    private Context mContext;
    private TruckInfo mInfo;
    private EditText mName;
    private EditText mAbout;
    private EditText mPrice;
    private ImageView mImage;
    private Button mSave;
    private Button mChooseNewImageButton;

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
        View rootView = inflater.inflate(R.layout.fragment_edit_menu, container, false);

        mImage = (ImageView)rootView.findViewById(R.id.EditMenuImage);
        mName = (EditText)rootView.findViewById(R.id.EditMenuName);
        mAbout = (EditText)rootView.findViewById(R.id.EditMenuInfo);
        mPrice = (EditText)rootView.findViewById(R.id.EditMenuPrice);
        mSave = (Button)rootView.findViewById(R.id.SaveShit);
        mChooseNewImageButton = (Button)rootView.findViewById(R.id.ChooseNewImageButton);

//        if(mInfo != null)
//        {
//            if(!mInfo.menu.isEmpty())
//            {
//                mName.setText(mInfo.menu.get());
//                mAbout.setText(mInfo.about);
//                mPrice.getText();
//                if (mInfo.image != null)
//                {
//                    mImage.setImageDrawable(mInfo.image);
//                }
//                else
//                {
//                    mImage.setImageResource(R.drawable.splash_icon);
//                }
//            }
//        }

        mSave.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mInfo.menu.add(new menuItem());
                mInfo.menu.get(mInfo.menu.size()-1).name = mName.getText().toString();
                mInfo.menu.get(mInfo.menu.size()-1).description = mAbout.getText().toString();
                mInfo.menu.get(mInfo.menu.size()-1).price = mPrice.getText().toString();
                mInfo.menu.get(mInfo.menu.size()-1).image = mImage.getDrawable();

                AWSInterface.getPlayer().EditTruckByID(mInfo);

                Intent profileIntent = new Intent(mContext, UserProfileActivity.class);
                profileIntent.putExtra("ID", mInfo.id);
                mContext.startActivity(profileIntent);
                //finish();
            }
        });

        mChooseNewImageButton.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 0);
            }
        });

        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            Uri targetUri = data.getData();
            //textTargetUri.setText(targetUri.toString());
            Bitmap bitmap;
            try {
                bitmap = BitmapFactory.decodeStream(mContext.getContentResolver().openInputStream(targetUri));
                Drawable drawable = new BitmapDrawable(getResources(), bitmap);
                mImage.setImageDrawable(drawable);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
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
