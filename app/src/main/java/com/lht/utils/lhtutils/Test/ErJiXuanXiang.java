package com.lht.utils.lhtutils.Test;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lht.utils.lhtutils.Activity.BaseActivity;
import com.lht.utils.lhtutils.Activity.OkhttpTool;
import com.lht.utils.lhtutils.Gson.XiangMuFenLeiGson;
import com.lht.utils.lhtutils.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by lht on 2017/3/14.
 */

public class ErJiXuanXiang extends BaseActivity {

    private ListView lv;
    private GridView gv;
    private DisplayMetrics metrics;
    private int GETINFO_ONE = 0x123;
    private int NETWORKEXCEPTION = 0x213;
    private XiangMuFenLeiGson xiangMuFenLeiGson;
    private List<XiangMuFenLeiGson.ListBean> list = new ArrayList<>();
    private List<XiangMuFenLeiGson.ListBean> list_xiao = new ArrayList<>();
    private LvAdapter lvAdapter;
    private GvAdapter gvAdapter;
    private int i;
    private int checkNum;
    private List<Boolean> list_isChecked = new ArrayList<>();
    private List<Boolean> list_jilu = new ArrayList<>();
    private Handler hd = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == GETINFO_ONE) {

                for (int i = 0; i < xiangMuFenLeiGson.getList().size(); i++) {
                    if (xiangMuFenLeiGson.getList().get(i).getParID().equals("0")) {
                        list.add(xiangMuFenLeiGson.getList().get(i));
                    }
                }
                lvAdapter.notifyDataSetChanged();
                selectClass2("1");
                initList();
            } else if (msg.what == NETWORKEXCEPTION) {

            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        setContentView(R.layout.erjicaidan_activity);

        lv = (ListView) findViewById(R.id.class1);
        gv = (GridView) findViewById(R.id.class2);


        OkhttpTool.getOkHttpTool().get("http://app.680.com/api/itemclass.ashx", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("lht", "==================e:" + e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String s = response.body().string();
                Log.d("lht", "==============Response:" + s);
                xiangMuFenLeiGson = new Gson().fromJson(s, XiangMuFenLeiGson.class);
                hd.sendEmptyMessage(GETINFO_ONE);

            }
        });
        lvAdapter = new LvAdapter();
        gvAdapter = new GvAdapter();
        lv.setAdapter(lvAdapter);
        gv.setAdapter(gvAdapter);


    }

    private void initList() {
        booleanList(list_jilu);
        for (int i = 0; i < list_xiao.size(); i++) {
            list_isChecked.add(false);
        }
    }

    private void booleanList(List boollist) {
        for (int i = 0; i < list.size(); i++) {
            boollist.add(false);
        }
    }

    public void selectClass2(String id) {
        list_xiao.clear();
        for (int i = 0; i < xiangMuFenLeiGson.getList().size(); i++) {
            if (xiangMuFenLeiGson.getList().get(i).getParID().equals(id)) {
                list_xiao.add(xiangMuFenLeiGson.getList().get(i));
            }
        }
        gvAdapter.notifyDataSetChanged();
    }

    class LvAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return list.size();
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
        public View getView(final int position, View convertView, ViewGroup parent) {

            if (convertView == null || convertView.getTag() == null) {
                convertView = LayoutInflater.from(ErJiXuanXiang.this).inflate(R.layout.class1_item, null);
                id = new ID();
                id.tv = (TextView) convertView.findViewById(R.id.name);
                convertView.setTag(id);
            } else {
                id = (ID) convertView.getTag();
            }

            id.tv.setText(list.get(position).getClassName());
            id.tv.setTextColor(getResources().getColor(R.color.colorInfo));
            id.tv.setTextSize(14);
            if (list_jilu.get(position)) {
                id.tv.setBackgroundColor(Color.parseColor("#666666"));
            } else {
                id.tv.setBackgroundColor(Color.parseColor("#ffffff"));
            }
            convertView.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            id.tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectClass2(list.get(position).getClassID());
                    cleanList(list_isChecked);
                    checkNum = 0;
                    //为保证只有一项被选择，即互斥，只能用循环把所有都遍历一次，目前暂时没有什么好办法
                    //目前思路是先把所有项都设成false，然后把点击那一项置为true
                    for (int i = 0; i < list.size(); i++) {
                        list_jilu.set(i, false);
                        if (!list_jilu.get(position)) {
                            list_jilu.set(position, true);
                        }
                    }
                    notifyDataSetChanged();
                }

            });
            return convertView;
        }

        class ID {
            TextView tv;
        }
    }

    class GvAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list_xiao.size();
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
        public View getView(final int position, View convertView, ViewGroup parent) {

            if (convertView == null || convertView.getTag() == null) {
                convertView = LayoutInflater.from(ErJiXuanXiang.this).inflate(R.layout.class1_item, null);
                id = new ID();
                id.tv = (TextView) convertView.findViewById(R.id.name);
                convertView.setTag(id);
            } else {
                id = (ID) convertView.getTag();
            }

            id.tv.setText(list_xiao.get(position).getClassName());
            id.tv.setTextColor(getResources().getColor(R.color.colorInfo));
            id.tv.setTextSize(12);
            if (position % 2 == 0) {
                convertView.setPadding(40, 20, 0, 20);
            } else {
                convertView.setPadding(0, 20, 20, 20);
            }
            if (list_isChecked.get(position)) {
                id.tv.setBackgroundColor(Color.parseColor("#f53a33"));
                //将字体颜色改为白色
                id.tv.setTextColor(android.graphics.Color.parseColor("#FFFFFF"));
            } else {
                id.tv.setBackgroundResource(R.drawable.fenlei_item_white);
                //没有选中则改为黑色
                id.tv.setTextColor(android.graphics.Color.parseColor("#333333"));
            }
            convertView.setLayoutParams(new AbsListView.LayoutParams(metrics.widthPixels / 7 * 2, ViewGroup.LayoutParams.WRAP_CONTENT));
            id.tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (list_isChecked.get(position)) {
                        Log.d("lht", "=====================第" + position + "项被取消");
                        list_isChecked.set(position, false);
                        checkNum--;
                    } else {
                        if (checkNum >= 5) {
                            Toast.makeText(ErJiXuanXiang.this, "最多只能选择5项", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Log.d("lht", "=====================第" + position + "项被选中");
                        list_isChecked.set(position, true);
                        checkNum++;
                    }
                    notifyDataSetChanged();
                }
            });

            return convertView;
        }

        class ID {
            TextView tv;
        }
    }

    private void cleanList(List<Boolean> mlist) {
        for (int i = 0; i < list_xiao.size(); i++) {
            mlist.set(i, false);
        }
    }

}
