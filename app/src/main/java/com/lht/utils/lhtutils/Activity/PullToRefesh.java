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
import android.widget.ListView;
import android.widget.TextView;

import com.lht.utils.lhtutils.R;

/**
 * Created by lht on 2017/2/20.
 */

public class PullToRefesh extends AppCompatActivity {

    private ListView mListView;
    private SwipeRefreshLayout sp;
    private Handler hd=new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what == 0x111) {
                sp.setRefreshing(false);
            }
        }
    };
    private BaseAdapter myAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return 120;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        TextView mTextView;
        int t = 0;

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            convertView = LayoutInflater.from(PullToRefesh.this).inflate(R.layout.list_item, null);
            mTextView = (TextView) convertView.findViewById(R.id.t);
            mTextView.setText("" + t++);

            return convertView;
        }
    };
    ;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiala);

        mListView = (ListView) findViewById(R.id.lsv);
        sp = (SwipeRefreshLayout) findViewById(R.id.srl);

        mListView.setAdapter(myAdapter);

        sp.setSize(0);
        sp.setColorSchemeColors(Color.parseColor("#ff3399"), Color.parseColor("#aa2299"), Color.parseColor("#cc5599"));
        sp.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        sp.setRefreshing(true);
                        myAdapter.notifyDataSetChanged();
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        hd.sendEmptyMessage(0x111);
                    }
                }).start();
            }
        });


    }


}
