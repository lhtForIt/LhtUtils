package com.lht.utils.lhtutils.Application;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;


/**
 * Created by lht on 2017/1/16.
 */

public class MyApplication extends Application{

    private Context context;

    @Override
    public void onCreate() {
        // 程序创建的时候执行
        Log.d("MyApplication","================onCreate");
        super.onCreate();
        context = getApplicationContext();
        EMOptions options = new EMOptions();
// 默认添加好友时，是不需要验证的，改成需要验证
        options.setAcceptInvitationAlways(false);
//自动登录开关
        options.setAutoLogin(false);
//初始化华为推送服务
//        options.setHuaweiPushAppId("10858777");
//初始化
        EMClient.getInstance().init(this, options);
//在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(true);
//easeUI初始化
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
