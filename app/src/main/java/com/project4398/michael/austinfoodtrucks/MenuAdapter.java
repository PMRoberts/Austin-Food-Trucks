package com.project4398.michael.austinfoodtrucks;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.project4398.michael.austinfoodtrucks.activities.EditMenuActivity;
import com.project4398.michael.austinfoodtrucks.activities.TruckProfileActivity;
import com.project4398.michael.austinfoodtrucks.activities.UserProfileActivity;

import java.text.NumberFormat;

/**
 * Displays menut items.
 *
 * @author Paul M. Roberts
 * @author Luis M. Rocha
 */
public class MenuAdapter extends ArrayAdapter<menuItem>
{

    private Context mContext;
    private final menuItem[] mValues;

    public MenuAdapter(Context context, menuItem[] values)
    {
        super(context, -1, values);
        mContext = context;
        mValues = values;
    }

    /**
     *
     * @param position The position in the array.
     * @param convertView - pushed in automatically but it is not used.
     * @param parent - The view that the new will get.
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.adapter_menu, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.MenuItemName);
        textView.setText(mValues[position].name);

        TextView textView2 = (TextView) rowView.findViewById(R.id.MenuItemPrice);
        textView2.setText(mValues[position].price);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.MenuItemImage);
        if(mValues[position].image != null) {
            imageView.setImageDrawable(mValues[position].image);
        }
        else
        {
            imageView.setImageResource(R.drawable.splash_icon);
        };
        final menuItem temp = mValues[position];
        rowView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (AWSInterface.getPlayer().ownersTruckID == temp.TruckId)
                {
                    Intent profileIntent = new Intent(mContext, EditMenuActivity.class);
                    profileIntent.putExtra("ID", temp.TruckId);
                    profileIntent.putExtra("MenuID", temp.id);
                    profileIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    mContext.startActivity(profileIntent);
                    //((Activity)mContext).finish();
                } else
                {
//                    Intent profileIntent = new Intent(mContext, EditMenuActivity.class);
//                    profileIntent.putExtra("ID", temp);
//                    mContext.startActivity(profileIntent);
                    View view = View.inflate(mContext,R.layout.dialog_menu_item,null);
                    TextView t = (TextView)view.findViewById(R.id.dialogMenuAbout);
                    t.setText(temp.description);
                    TextView t2 = (TextView)view.findViewById(R.id.dialogMenuPrice);
                    t2.setText(temp.price);
                    ImageView IV = (ImageView)view.findViewById(R.id.dialogMenuImage);
                    if (temp.image != null)
                    {
                        IV.setImageDrawable(temp.image);
                    }
                    else
                    {
                        IV.setImageResource(R.drawable.splash_icon);
                    }

                    View view2 = View.inflate(mContext,R.layout.dialog_custom_title,null);
                    TextView t3 = (TextView)view2.findViewById(R.id.DialogCustomTitle);
                    t3.setText(temp.name);

                    new AlertDialog.Builder(mContext)
                            //.setTitle(temp.name)
                            .setCustomTitle(view2)
                            .setView(view)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete
                                }
                            })
                            .show();
                }
            }
        });

        return rowView;
    }

}
