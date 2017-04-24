package com.lht.utils.lhtutils.WXPay;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.lht.utils.lhtutils.Activity.BaseActivity;
import com.lht.utils.lhtutils.R;

/**
 * Created by lht on 2017/4/5.
 */

public class PayActivity extends BaseActivity{

    private Button bt;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_activity);

        bt = (Button) findViewById(R.id.bt);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetPrepayIdTask get = new GetPrepayIdTask(PayActivity.this, "784561461231", "时间财富会员【小明】充值", "5");
                get.execute();
            }
        });



    }
}
