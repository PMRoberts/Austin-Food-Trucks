package com.project4398.michael.austinfoodtrucks;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.project4398.michael.austinfoodtrucks.activities.TruckProfileActivity;
import com.project4398.michael.austinfoodtrucks.activities.UserProfileActivity;
import com.project4398.michael.austinfoodtrucks.fragments.TruckListFragment;

/**
 * Created by PRoberts on 4/21/15.
 */
public class TruckListAdapter extends CursorAdapter
{
    Context mContext;
    public TruckListAdapter(Context context, Cursor c)
    {
        super(context, c, 0);
        mContext = context;
    }
    @Override @SuppressLint("NewApi")
    public void bindView(View view, Context context, Cursor cursor)
    {
        TruckListInfoHolder holder = (TruckListInfoHolder)view.getTag();

        holder.name.setText(cursor.getString(cursor.getColumnIndex("name")));
        holder.foodType.setText(cursor.getString(cursor.getColumnIndex("foodType")));
        holder.distance.setText(cursor.getString(cursor.getColumnIndex("distance")));
        holder.truckInfo = TruckListFragment.TLITemp.get(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id"))));
        final TruckListInfo temp = holder.truckInfo;

        holder.image.setImageResource(R.drawable.soundcloud_default);
        if(cursor.getString(cursor.getColumnIndex("favorite")).equals("true"))
        {
            holder.favorite.setImageResource(R.drawable.button_soundcloud_repeat_on);
        }
        else
        {
            holder.favorite.setImageResource(R.drawable.button_soundcloud_repeat_off);
        }

        holder.image.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(temp != null)
                {

                    Intent profileIntent = new Intent(mContext, UserProfileActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("info", temp);
                    profileIntent.putExtras(bundle);
                    mContext.startActivity(profileIntent);
                }
            }
        });

    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent)
    {

        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.truck_list_adapter, null);

        TruckListInfoHolder holder = new TruckListInfoHolder();
        holder.image = (ImageView)view.findViewById(R.id.TruckInfoImage);
        holder.name = (TextView)view.findViewById(R.id.TruckInfoName);
        holder.foodType = (TextView)view.findViewById(R.id.TruckInfoTypes);
        holder.distance= (TextView)view.findViewById(R.id.TruckInfoDistance);
        holder.favorite= (ImageView)view.findViewById(R.id.TruckInfoFavorite);

        view.setTag(holder);
        return(view);
    }


}
