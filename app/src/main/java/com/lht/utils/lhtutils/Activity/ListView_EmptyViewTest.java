package com.lht.utils.lhtutils.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.lht.utils.lhtutils.R;

/**
 * Created by lht on 2017/3/10.
 */

public class ListView_EmptyViewTest extends BaseActivity {

    private ListView listView;
    private int t;
    private EmptySwipeRefreshLayout esrl;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_empty);


        listView = (ListView) findViewById(R.id.lv);

        View v = LayoutInflater.from(this).inflate(R.layout.empty, null);
        ((ViewGroup) listView.getParent()).addView(v);
        listView.setEmptyView(v);
        listView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return 60;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                LinearLayout ll = new LinearLayout(ListView_EmptyViewTest.this);
                TextView tv = new TextView(ListView_EmptyViewTest.this);
                ll.addView(tv);

                tv.setText("" + (++t));


                return ll;
            }
        });

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                boolean enable = false;
                if (listView != null && listView.getChildCount() > 0) {
                    // 检查listView第一个item是否可见
                    boolean firstItemVisible = listView.getFirstVisiblePosition() == 0;
                    // 检查第一个item的顶部是否可见
                    boolean topOfFirstItemVisible = listView.getChildAt(0).getTop() == 0;
                    // 启用或者禁用SwipeRefreshLayout刷新标识
                    enable = firstItemVisible && topOfFirstItemVisible;
                } else if (listView != null && listView.getChildCount() == 0) {
                    // 没有数据的时候允许刷新
                    enable = true;
                }
                    // 把标识传给swipeRefreshLayout
                esrl.setEnabled(enable);
            }
        });

        listView.setAdapter(null);


    }
}
