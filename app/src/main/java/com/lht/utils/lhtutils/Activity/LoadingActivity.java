package com.lht.utils.lhtutils.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.lht.utils.lhtutils.R;
import com.lht.utils.lhtutils.View.LoadingDialog;

/**
 * Created by lht on 2017/3/8.
 */

public class LoadingActivity extends AppCompatActivity {


   LoadingDialog ld;
    private TextView tv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loadint_activity);

        tv = (TextView) findViewById(R.id.tv);
        ld=new LoadingDialog(this).setMessage("正在加载中....");


        ld.show();


        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ld.dismiss();
            }
        });
    }
}
