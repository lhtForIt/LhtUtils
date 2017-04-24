package com.lht.utils.lhtutils.HuanXin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.lht.utils.lhtutils.Activity.BaseActivity;
import com.lht.utils.lhtutils.R;

/**
 * Created by lht on 2017/3/22.
 */

public class Register extends BaseActivity {

    private EditText et;
    private EditText et1;
    private EditText et2;
    private Button bt;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        et = (EditText) findViewById(R.id.et);
        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);

        bt = (Button) findViewById(R.id.bt);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!et.getText().toString().equals("") && et1.getText().toString().equals(et2.getText().toString()) && !et1.getText().toString().equals("")) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                EMClient.getInstance().createAccount(et.getText().toString(), et1.getText().toString());
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(Register.this, "注册成功!", Toast.LENGTH_SHORT).show();
                                        Intent in = getIntent();
                                        in.putExtra("name", et.getText().toString());
                                        in.putExtra("pass",et1.getText().toString());
                                        setResult(1, in);
                                        finish();
                                    }
                                });
                            } catch (HyphenateException e) {
                                Log.d("lht", "=====================e:" + e.toString());
                                e.printStackTrace();
                            }
                        }
                    }).start();


                }
            }
        });


    }
}
