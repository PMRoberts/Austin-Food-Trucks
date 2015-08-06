package com.project4398.michael.austinfoodtrucks;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * A standard class; a relative view that automatically creates the width and height of the screen
 *
 * @author Paul M. Roberts
 * @author Luis M. Rocha
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
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
