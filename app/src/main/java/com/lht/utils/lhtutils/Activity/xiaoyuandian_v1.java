package com.lht.utils.lhtutils.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by lht on 2017/2/8.
 */

@SuppressLint("AppCompatCustomView")
public class xiaoyuandian_v1 extends TextView{
    public xiaoyuandian_v1(Context context, @Nullable AttributeSet attrs) {

        super(context, attrs);


    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(measureWidth(widthMeasureSpec),measureHight(heightMeasureSpec));
    }

    private int measureWidth(int measureSpec) {

        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specMode;
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
            result = specMode;
        } else {
            result = 200;
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }

        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p = new Paint();
        canvas.drawCircle(50,150,50,p);
        canvas.drawColor(Color.RED);



    }
}
