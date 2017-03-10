package com.lht.utils.lhtutils.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.lht.utils.lhtutils.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lht on 2017/2/23.
 */

public class PulToRefresh extends AppCompatActivity {


    private ListView listView;
    private SuperSwipeRefreshLayout swipeRefreshLayout;

    List<String> data;
    ArrayAdapter<String> a;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiala);

        listView = (ListView) findViewById(R.id.lsv);
        a = new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1, getData());
        listView.setAdapter(a);
        swipeRefreshLayout = (SuperSwipeRefreshLayout) findViewById(R.id.srl);
        swipeRefreshLayout.setFooterView(LayoutInflater.from(this).inflate(R.layout.loadmore_default_footer, null));
        swipeRefreshLayout
                .setOnPullRefreshListener(new SuperSwipeRefreshLayout.OnPullRefreshListener() {

                    @Override
                    public void onRefresh() {
                        new Handler().postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                swipeRefreshLayout.setRefreshing(false);
                            }
                        }, 2000);
                    }

                    @Override
                    public void onPullDistance(int distance) {
                        System.out.println("debug:distance = " + distance);
                        // myAdapter.updateHeaderHeight(distance);
                    }

                    @Override
                    public void onPullEnable(boolean enable) {
                    }
                });

        swipeRefreshLayout.setOnPushLoadMoreListener(new SuperSwipeRefreshLayout.OnPushLoadMoreListener() {
            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {

                        for (int i = 0; i < 20; i++) {
                            data.add("item -- " + i);
                        }
                        a.notifyDataSetChanged();
                        swipeRefreshLayout.setLoadMore(false);
                    }
                }, 2000);
            }

            @Override
            public void onPushDistance(int distance) {

            }

            @Override
            public void onPushEnable(boolean enable) {

            }
        });

    }






    private List<String> getData() {
        data = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            data.add("item -- " + i);
        }
        return data;
    }


}












