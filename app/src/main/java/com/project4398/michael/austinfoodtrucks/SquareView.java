package com.project4398.michael.austinfoodtrucks;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Michael on 7/23/2015.
 */
public class SquareView extends RelativeLayout{

    LayoutInflater mInflater;
    public SquareView(Context context) {
        super(context);
        mInflater = LayoutInflater.from(context);
        init();

    }
    public SquareView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        mInflater = LayoutInflater.from(context);
        init();
    }
    public SquareView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mInflater = LayoutInflater.from(context);
        init();
    }
    public void init()
    {
//        mInflater.inflate(R.layout.custom_view, this, true);
//        TextView tv = (TextView) v.findViewById(R.id.textView1);
//        tv.setText(" Custom RelativeLayout");
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
