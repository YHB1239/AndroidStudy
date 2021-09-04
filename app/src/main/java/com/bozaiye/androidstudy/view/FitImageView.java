package com.bozaiye.androidstudy.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;


public class FitImageView extends ImageView {

    public FitImageView(Context context) {
        super(context);
    }

    public FitImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        Drawable drawable = getDrawable();

        if(drawable!=null){
//            int width = MeasureSpec.getSize(widthMeasureSpec);
//            int height = (int) Math.ceil((float) width * (float) drawable.getIntrinsicHeight() / (float) drawable.getIntrinsicWidth());
//            setMeasuredDimension(width, height);
            int height = MeasureSpec.getSize(heightMeasureSpec);
            int width = (int) Math.ceil((float) height * (float) drawable.getIntrinsicWidth() / (float) drawable.getIntrinsicHeight());
            setMeasuredDimension(width, height);
        }else{
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

}