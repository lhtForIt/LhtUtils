package com.lht.utils.lhtutils.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lht.utils.lhtutils.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lht on 2017/2/23.
 */

public class PllToRefresh extends AppCompatActivity implements MyListView.OnLoadListener{

    private SwipeRefreshLayout swp;
    private MyListView list;
    private List<Info> list_info = new ArrayList<>();
    private int[] image = {R.mipmap.biji,R.mipmap.biji,R.mipmap.biji,R.mipmap.biji,R.mipmap.biji,R.mipmap.biji,R.mipmap.biji,R.mipmap.biji,R.mipmap.biji,R.mipmap.biji};
    private BaseAdapter na;
    private Handler hd = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0x123) {
                na.notifyDataSetChanged();
                swp.setRefreshing(false);
            } else {
                na.notifyDataSetChanged();
                list.onLoadComplete();
            }
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listv);

        swp = (SwipeRefreshLayout) findViewById(R.id.swr);
        list = (MyListView) findViewById(R.id.mylv);


        swp.setColorSchemeColors(Color.parseColor("#ff3399"), Color.parseColor("#aa2299"), Color.parseColor("#cc5599"));
        na=new BaseAdapter() {
            @Override
            public int getCount() {
                return list_info.size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            ID id;
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null || convertView.getTag() == null) {

                    convertView = LayoutInflater.from(PllToRefresh.this).inflate(R.layout.listview_view, null);
                    id = new ID();
                    id.iv = (ImageView) convertView.findViewById(R.id.N);
                    id.tv = (TextView) convertView.findViewById(R.id.N1);
                    convertView.setTag(id);

                } else {
                    id = (ID) convertView.getTag();
                }

                id.iv.setImageResource(list_info.get(position).getImage());
                id.tv.setText(list_info.get(position).getItem());

                return convertView;
            }

            class  ID{
                private ImageView iv;
                private TextView tv;
            }
        };
        list.setAdapter(na);
        swp.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        swp.setRefreshing(true);
                        list_info.clear();
                        for (int i = 0; i < image.length; i++) {
                            Info info = new Info();
                            info.setImage(image[i]);
                            info.setItem("item-----"+i);
                            list_info.add(info);
                        }
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        hd.sendEmptyMessage(0x123);
                    }
                }).start();
            }
        });

        for (int i = 0; i < image.length; i++) {
            Info info = new Info();
            info.setImage(image[i]);
            info.setItem("item-----"+i);
            list_info.add(info);
        }

        list.setEmptyView(findViewById(R.id.mylv));

        list.setOnLoadListener(this);


    }

    @Override
    public void onLoad() {

        new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < image.length; i++) {
                    Info info = new Info();
                    info.setImage(image[i]);
                    info.setItem("item-----"+i);
                    list_info.add(info);
                }
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                hd.sendEmptyMessage(0x111);
            }
        }).start();


    }
}
