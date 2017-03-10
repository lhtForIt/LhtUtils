package com.lht.utils.lhtutils.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.lht.utils.lhtutils.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by lht on 2017/2/24.
 */

public class OkHttpTest extends AppCompatActivity {

    private TextView tv;
    private Handler hd = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0x123) {
                tv.setText(msg.getData().getString("responseBody"));
            }
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.okhttp_activity);

        tv = (TextView) findViewById(R.id.tv);

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()

                //缓存
//                .cache(new Cache(new File(getExternalCacheDir() + "/mycache"), 1024 * 4))
                //读取时间超时
                .readTimeout(10, TimeUnit.SECONDS)
                //写入时间超时
                .writeTimeout(10, TimeUnit.SECONDS)
                //连接超时
                .connectTimeout(10, TimeUnit.SECONDS)
                //socket 轮播简隔
//                .pingInterval(10, TimeUnit.SECONDS)
                .cookieJar(new CookieJar() {
                    @Override
                    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                        //保存cookie信息,密码，表单等信息
                    }

                    @Override
                    public List<Cookie> loadForRequest(HttpUrl url) {
                        //从保存位置读取，注意此处不能为空，否则会导致空指针

                        return new ArrayList<Cookie>();
                    }
                })

                // 此种拦截器将会在请求开始的时候调用
//                .addInterceptor(new Interceptor() {
//                    @Override
//                    public Response intercept(Chain chain) throws IOException {
//                        return null;
//                    }
//                })

                // 连接成功，读写数据前调用
//                .addNetworkInterceptor(new Interceptor() {
//                    @Override
//                    public Response intercept(Chain chain) throws IOException {
//                        Log.d("lht", "======================连接成功");
//                        return null;
//                    }
//                })

                //创建一个证书工厂
//                .sslSocketFactory(SSLSocketFactory, X509TrustManager)


                .build();

//        FormBody formBody = new FormBody.Builder()
//                .add("name", "dsd")
//                .build();

        //异步http GET请求
        //异步和同步区别，异步主要处理高并发，形成队列，运用线程池，最大限度处理并发，
        // 同步在创建请求对象时就发起请求，优点是及时，缺点是在处理并发时有欠缺
        Request request = new Request.Builder().url("http://app.680.com/api/v3/index_gz_data.ashx")
                .get()
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("lht", "返回失败,异常为：" + e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                ResponseBody responseBody = response.body();
                final String s = responseBody.string();
                Log.d("lht", "=================onResponse:" + s);
                //response.body().string()调用了多次会导致java.lang.IllegalStateException: closed异常，
                // string()仅可调用一次
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message ms = new Message();
                        Bundle b = new Bundle();
                        b.putString("responseBody", s);
                        ms.setData(b);
                        ms.what = 0x123;
                        hd.sendMessage(ms);
                    }
                }).start();
            }
        });

    }

}
