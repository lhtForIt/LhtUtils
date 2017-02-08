package com.lht.utils.lhtutils.Activity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;


/**
 * Created by lht on 2017/2/7.
 */

public class xiaoxiyuandian extends View{


    private Context context;
    private int count;
    private int textSize;

    public xiaoxiyuandian(Context context, @Nullable AttributeSet attrs) {

        super(context, attrs);
        this.context = context;



    }


    public xiaoxiyuandian(Context context,int count,int textSize) {
        super(context,null,0);
        this.count = count;
        this.textSize = textSize;

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHight(heightMeasureSpec));

    }




    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p = new Paint();
        p.setColor(Color.RED);
        p.setTextAlign(Paint.Align.CENTER);
        canvas.drawColor(Color.parseColor("#00000000"));
        canvas.drawCircle(30,130,30,p);
        p.setColor(Color.WHITE);
        p.setTextAlign(Paint.Align.CENTER);
        p.setTextSize(textSize);
        canvas.drawText(count+"",30,150,p);

    }

    private int measureWidth(int measureSpec) {

        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);


        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = 200;
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }

        return result;
    }


    private int measureHight(int measureSpec) {

        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);


        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = 200;
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }

        return result;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

}
