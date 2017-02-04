package com.lht.message.localbrodcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.LocationManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private LocalBroadcastManager localBroadcastManager;
    private Button bt;
    private MyGuangBo mgb;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        localBroadcastManager = localBroadcastManager.getInstance(this);
        bt = (Button) findViewById(R.id.bt);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent("com.lht.guangbo");
                localBroadcastManager.sendBroadcast(in);
            }
        });

        IntentFilter inf = new IntentFilter();
        inf.addAction("com.lht.guangbo");
        mgb = new MyGuangBo();
        localBroadcastManager.registerReceiver(mgb, inf);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(mgb);
    }

    class MyGuangBo extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(MainActivity.this, "收到广播了", Toast.LENGTH_SHORT).show();
        }
    }



}
