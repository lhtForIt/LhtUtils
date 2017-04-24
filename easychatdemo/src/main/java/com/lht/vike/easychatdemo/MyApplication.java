package com.lht.vike.easychatdemo;

import android.app.Application;
import android.content.Context;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;

/**
 * Created by lht on 2017/3/23.
 */

public class MyApplication extends Application{


    private Context context;


    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        EMOptions options = new EMOptions();
// 默认添加好友时，是不需要验证的，改成需要验证
        options.setAcceptInvitationAlways(false);
//自动登录开关
        options.setAutoLogin(false);
//初始化华为推送服务
        options.setHuaweiPushAppId("10858777");
//初始化
        EMClient.getInstance().init(this, options);
//在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(true);
    }
}
