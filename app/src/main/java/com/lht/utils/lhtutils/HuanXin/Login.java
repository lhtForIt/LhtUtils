package com.lht.utils.lhtutils.HuanXin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hyphenate.EMCallBack;
import com.hyphenate.EMConnectionListener;
import com.hyphenate.EMError;
import com.hyphenate.chat.EMClient;
import com.hyphenate.util.NetUtils;
import com.lht.utils.lhtutils.Activity.BaseActivity;
import com.lht.utils.lhtutils.Activity.Chat;
import com.lht.utils.lhtutils.R;

/**
 * Created by lht on 2017/3/22.
 */

public class Login extends BaseActivity{

    private EditText et;
    private EditText et1;
    private Button bt;
    private Button bt1;
    private Button bt2;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

//        EMClient.getInstance().chatManager().loadAllConversations();
//        EMClient.getInstance().groupManager().loadAllGroups();

        EMClient.getInstance().addConnectionListener(new MyConnectionListener());
        et = (EditText) findViewById(R.id.et);
        et1 = (EditText) findViewById(R.id.et1);
        bt = (Button) findViewById(R.id.bt);
        bt1 = (Button) findViewById(R.id.bt1);
        bt2 = (Button) findViewById(R.id.bt2);


        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EMClient.getInstance().login(et.getText().toString(), et1.getText().toString(), new EMCallBack() {
                    @Override
                    public void onSuccess() {
                        //为了保证进入主页面后本地会话和群组都 load 完毕
                        EMClient.getInstance().chatManager().loadAllConversations();
                        EMClient.getInstance().groupManager().loadAllGroups();
                        Log.d("lht", "==================登录成功!");
//                        Intent in = new Intent(Login.this, Chat.class);
                        Intent in = new Intent(Login.this, Chat.class);
                        startActivity(in);
                    }

                    @Override
                    public void onError(int code, String error) {
                        Log.d("lht", "====================error:"+error);
                    }

                    @Override
                    public void onProgress(int progress, String status) {
                        Log.d("lht", "====================progress:"+progress+",status："+status);
                    }
                });

            }
        });

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(Login.this, Register.class), 1);
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EMClient.getInstance().logout(true, new EMCallBack() {
                    @Override
                    public void onSuccess() {
                        Log.d("lht", "==================退出登录成功!");
                    }

                    @Override
                    public void onError(int code, String error) {
                        Log.d("lht", "====================error:"+error);
                    }

                    @Override
                    public void onProgress(int progress, String status) {
                        Log.d("lht", "====================progress:"+progress+",status："+status);
                    }
                });
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1&&resultCode==1) {
            String username = data.getStringExtra("name");
            String password = data.getStringExtra("pass");
            et.setText(username);
            et1.setText(password);

        }
    }


    //实现ConnectionListener接口
    private class MyConnectionListener implements EMConnectionListener {
        @Override
        public void onConnected() {
        }
        @Override
        public void onDisconnected(final int error) {
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    if(error == EMError.USER_REMOVED){
                        // 显示帐号已经被移除
                    }else if (error == EMError.USER_LOGIN_ANOTHER_DEVICE) {
                        // 显示帐号在其他设备登录
                    } else {
                        if (NetUtils.hasNetwork(Login.this)) {

                        }
                        //连接不到聊天服务器
                        else {
                            Log.d("lht", "=================网络不好进来了");
                        }
                        //当前网络不可用，请检查网络设置
                    }
                }
            });
        }
    }
}
