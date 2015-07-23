package com.project4398.michael.austinfoodtrucks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * Created by Michael on 7/22/2015.
 */
public class MenuAdapter extends ArrayAdapter<menuItem>
{

    private Context mContext;
    private final menuItem[] mValues;
    NumberFormat formatter = NumberFormat.getCurrencyInstance();

    public MenuAdapter(Context context, menuItem[] values)
    {
        super(context, -1, values);
        mContext = context;
        mValues = values;
    }

//    public void SwapArrays(menuItem[] values)
//    {
//        mValues = values;
//    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.adapter_menu, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.MenuItemName);
        textView.setText(mValues[position].name);
        TextView textView2 = (TextView) rowView.findViewById(R.id.MenuItemDescription);
        textView2.setText(mValues[position].description);
        TextView textView3 = (TextView) rowView.findViewById(R.id.MenuItemPrice);
        String output = formatter.format(mValues[position].price);
        textView3.setText(output);

        return rowView;
    }

}
