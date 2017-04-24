package com.lht.utils.lhtutils.Kongjian_5_0;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lht.utils.lhtutils.Activity.BaseActivity;
import com.lht.utils.lhtutils.Gson.XiangMuFenLeiGson;
import com.lht.utils.lhtutils.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lht on 2017/3/14.
 */

public class RecyclerViewTest extends BaseActivity{

    private RecyclerView rv;
    private RecyclerView rv1;
    private int GETINFO_ONE = 0x123;
    private int NETWORKEXCEPTION = 0x213;
    private XiangMuFenLeiGson xiangMuFenLeiGson;
    private List<XiangMuFenLeiGson.ListBean> list = new ArrayList<>();
    private List<XiangMuFenLeiGson.ListBean> list_short = new ArrayList<>();
    //RecyclerView分割线
    private RecyclerView.ItemDecoration itemDecoration;
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


            } else if (msg.what == NETWORKEXCEPTION) {

            }
        }
    };
    MyAdapter myAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_activity);

        rv = (RecyclerView) findViewById(R.id.class1);
        rv1 = (RecyclerView) findViewById(R.id.class2);

        rv.setLayoutManager(new LinearLayoutManager(this));
        rv1.setLayoutManager(new GridLayoutManager(this,2));


        itemDecoration = new RecyclerView.ItemDecoration() {

            private Drawable drawable = getResources().getDrawable(R.drawable.line5);

            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDraw(c, parent, state);
            }

            @Override
            public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDrawOver(c, parent, state);
                final int left = parent.getPaddingLeft();
                final int right = parent.getWidth() - parent.getPaddingRight();
                final int childCount = parent.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    final View child = parent.getChildAt(i);
                    final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                            .getLayoutParams();
                    //以下计算主要用来确定绘制的位置
                    final int top = child.getBottom() + params.bottomMargin;
                    final int bottom = top + drawable.getIntrinsicHeight();
                    drawable.setBounds(left, top, right, bottom);
                    drawable.draw(c);
                }

            }

            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.set(0, 0, 0, drawable.getIntrinsicWidth());
            }
        };
        rv.addItemDecoration(itemDecoration);
        myAdapter = new MyAdapter();
        rv.setAdapter(myAdapter);
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder>{

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View v = LayoutInflater.from(RecyclerViewTest.this).inflate(R.layout.recycleview_item, null);
            MyHolder vh = new MyHolder(v);


            return vh;
        }

        @Override
        public void onBindViewHolder(MyHolder holder, int position) {

            holder.tv.setText(""+position);

        }


        @Override
        public int getItemCount() {
//            if (type == 0) {
                return 20;
//            } else if (type == 1) {
//                return list_short.size();
//            }
//            return 0;
        }

        class MyHolder extends RecyclerView.ViewHolder{

            public TextView tv;
            public MyHolder(View itemView) {
                super(itemView);
                tv = (TextView) itemView.findViewById(R.id.tv);
            }
        }
    }

}
