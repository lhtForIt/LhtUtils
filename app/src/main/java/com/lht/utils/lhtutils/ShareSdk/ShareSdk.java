package com.lht.utils.lhtutils.ShareSdk;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.lht.utils.lhtutils.Activity.BaseActivity;
import com.lht.utils.lhtutils.Activity.OkhttpTool;
import com.lht.utils.lhtutils.Gson.UserInfo;
import com.lht.utils.lhtutils.Other.LhtConfig;
import com.lht.utils.lhtutils.R;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by lht on 2017/3/21.
 */

public class ShareSdk extends BaseActivity{

    private Button bt;
    private Button bt1;
    private UserInfo userInfo;
    private Map<String, Object> map = new HashMap<>();
    private Handler hd = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0x123) {

            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sharesdk_activity);

        bt = (Button) findViewById(R.id.bt);
        bt1 = (Button) findViewById(R.id.bt1);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ShareSDK.initSDK(ShareSdk.this);
                final Platform qq = ShareSDK.getPlatform("QQ");
                qq.setPlatformActionListener(new PlatformActionListener() {
                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {

                        platform.getDb().exportData();
                        String openId = qq.getDb().getUserId();
                        String accessToken = qq.getDb().getToken();
                        Log.d("lht", "===============openId:" + openId);
                        Log.d("lht", "===============accessToken:" + accessToken);
                        Log.d("lht", "===============hashMap:" + hashMap.toString());
                        map.put("openid", openId);
                        map.put("access_token", accessToken);
                        map.put("mobile_id", LhtConfig.getHouse_CID(ShareSdk.this));
                        OkhttpTool.getOkHttpTool().post("http://app.680.com/api/v2/login.ashx", map, new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {

                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {

                                String s = response.body().string();
                                Log.d("lht", "=================response:" + s);

                            }
                        });

                    }

                    @Override
                    public void onError(Platform platform, int i, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(Platform platform, int i) {

                    }
                });

                qq.showUser(null);
//                qq.authorize();

            }
        });

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LhtConfig.showShare(ShareSdk.this);
            }
        });




    }
}
