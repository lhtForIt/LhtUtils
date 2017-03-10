package com.lht.utils.lhtutils.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.lht.utils.lhtutils.Other.LhtConfig;
import com.lht.utils.lhtutils.Pay.Alipay;
import com.lht.utils.lhtutils.R;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by lht on 2017/3/8.
 */

public class AliPlayTest extends AppCompatActivity{

    private Button bt;
    public static String PARTNER;
    public static String SELLER;
    public static String public_key;
    public static String private_key;
    private TestGaon testGaon;
    private Handler hd=new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0x123) {
                Log.d("lht", "===================进来了");
                PARTNER = testGaon.getAlipay_config().getPartner();
                SELLER = testGaon.getAlipay_config().getSeller();
                public_key = testGaon.getAlipay_config().getPublic_key();
                private_key = testGaon.getAlipay_config().getPrivate_key();
                Log.d("lht", "===================出去了");
                Log.d("lht", "===================PARTNER："+PARTNER);
                Log.d("lht", "===================SELLER："+SELLER);
                Log.d("lht", "===================public_key："+public_key);
                Log.d("lht", "===================private_key："+private_key);
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alipay_activity);

        bt = (Button) findViewById(R.id.bt);

        Log.d("lht", "===================PARTNER："+PARTNER);
        Log.d("lht", "===================SELLER："+SELLER);
        Log.d("lht", "===================public_key："+public_key);
        Log.d("lht", "===================private_key："+private_key);

        OkhttpTool.getOkHttpTool().get(LhtConfig.GUZHU_INFO, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("lht", "=============================IOException:"+e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String s = response.body().string();
                Log.d("lht", "==========================Response:" + s);
                testGaon = new Gson().fromJson(s, TestGaon.class);
                hd.sendEmptyMessage(0x123);
            }
        });





        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Alipay.pay(AliPlayTest.this,LhtConfig.getHander(AliPlayTest.this,"333"),Alipay.getOrderInfo("知识产权", "时间财富商标注册订单:","200","1232154645"));

            }
        });


    }
}
