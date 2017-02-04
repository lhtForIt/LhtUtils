package com.lht.utils.lhtutils.Application;

import android.app.Application;
import android.content.Context;
import android.util.Log;


/**
 * Created by lht on 2017/1/16.
 */

public class MyApplication extends Application{

    Context context;



    @Override
    public void onCreate() {
        // 程序创建的时候执行
        Log.d("MyApplication","================onCreate");
        super.onCreate();
        context = getApplicationContext();
    }

    @Override
    public void onTerminate() {
        // 程序终止的时候执行
        Log.d("MyApplication","================onTerminate");
        super.onTerminate();
    }

    @Override
    public void onTrimMemory(int level) {
        // 程序在内存清理的时候执行（回收内存）
        Log.d("MyApplication","================onTrimMemory");
        super.onTrimMemory(level);
    }

    @Override
    public void onLowMemory() {
        // 低内存的时候执行
        Log.d("MyApplication", "================onLowMemory");
        super.onLowMemory();
    }
}
