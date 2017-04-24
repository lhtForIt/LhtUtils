package com.lht.utils.lhtutils.Test;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lht.utils.lhtutils.R;

/**
 * Created by lht on 2017/3/28.
 */

public class LHTToast extends AppCompatActivity {


    private TextView tt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lhttoast_activity);

        tt = (TextView) findViewById(R.id.tt);


        tt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast t = new Toast(LHTToast.this);
                View v1 = LayoutInflater.from(LHTToast.this).inflate(R.layout.toast_item, null);
                TextView tv = (TextView) v1.findViewById(R.id.tv);
                tv.setTextColor(Color.parseColor("#ffffff"));
                tv.setText("我是测试消息");
                t.setView(v1);
                t.setDuration(Toast.LENGTH_SHORT);
                t.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL,0,100);
                t.show();
            }
        });


    }
}
