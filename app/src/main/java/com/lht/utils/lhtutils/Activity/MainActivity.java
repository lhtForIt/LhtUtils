package com.lht.utils.lhtutils.Activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.lht.utils.lhtutils.R;


public class MainActivity extends BaseActivity {

    private ImageView back;
    private TextView content;
    private ImageView setting;
    private TextView tv;
    private EditText et;
    private MyBroadcastreceiver myBroadcastreceiver;
    private RadioButton radioButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        back = (ImageView) findViewById(R.id.back);
        content = (TextView) findViewById(R.id.content);
        setting = (ImageView) findViewById(R.id.Settings);
        tv = (TextView) findViewById(R.id.tt);
        et = (EditText) findViewById(R.id.et);
        radioButton = (RadioButton) findViewById(R.id.rb2);
        BadgeViewNotAccurate badge = new BadgeViewNotAccurate(MainActivity.this);
        badge.setTargetView(radioButton);
        badge.setBadgeCount(99);
//        BadgeView badge = new BadgeView(this, radioButton);
//        badge.setText("9");
//        badge.show();
        IntentFilter inf = new IntentFilter();
        inf.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        myBroadcastreceiver = new MyBroadcastreceiver();
        registerReceiver(myBroadcastreceiver, inf);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        content.setText("我是测试内容");
        content.setGravity(Gravity.CENTER);
        content.setTextSize(28);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "设置被点击了", Toast.LENGTH_SHORT).show();
            }
        });


        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tv.setText(et.getText().toString());
                if (s.length()>=30) {

                    Intent in = new Intent(MainActivity.this, Chat.class);
                    startActivity(in);

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }


    class MyBroadcastreceiver extends BroadcastReceiver {

        /**
         * TYPE_NONE        = -1
         * TYPE_MOBILE      = 0
         * TYPE_WIFI        = 1
         * TYPE_MOBILE_MMS  = 2
         * TYPE_MOBILE_SUPL = 3
         * TYPE_MOBILE_DUN  = 4
         * TYPE_MOBILE_HIPRI = 5
         * TYPE_WIMAX       = 6
         * TYPE_BLUETOOTH   = 7
         * TYPE_DUMMY       = 8
         * TYPE_ETHERNET    = 9
         * TYPE_MOBILE_FOTA = 10
         * TYPE_MOBILE_IMS  = 11
         * TYPE_MOBILE_CBS  = 12
         * TYPE_WIFI_P2P    = 13
         * TYPE_MOBILE_IA = 14
         * TYPE_MOBILE_EMERGENCY = 15
         * TYPE_PROXY = 16
         * TYPE_VPN = 17
         *
         *
         *
         * 擦，不知道为什么，Toast打的出来，log信息打不出来，可能是as的bug，不过经过测试这个功能没什么问题
         *
         *
         */

        //
        @Override
        public void onReceive(Context context, Intent intent) {

            ConnectivityManager com = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo ni = com.getActiveNetworkInfo();
            if (ni != null && ni.isAvailable()) {
                Log.d("lttt", "连接模式==========================" + ni.getType());
                Toast.makeText(MainActivity.this,"连接模式==========================" + ni.getType(),Toast.LENGTH_SHORT).show();
            } else {
                Log.d("lttt", "==========================当前无网络!");
                Toast.makeText(MainActivity.this, "==========================当前无网络!",Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myBroadcastreceiver);

    }
}
