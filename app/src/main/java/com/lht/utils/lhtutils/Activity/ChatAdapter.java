package com.lht.utils.lhtutils.Activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lht.utils.lhtutils.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lht on 2017/1/23.
 */

public class ChatAdapter extends BaseAdapter {

    private List<Msg> list_msg = new ArrayList<>();
    private Context context;

    public ChatAdapter(Context context,List list) {
        this.context = context;
        this.list_msg = list;
    }

    @Override
    public int getItemViewType(int position) {
        return ((Msg)getItem(position)).getType();
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override

    public int getCount() {
        return list_msg.size();
    }

    @Override
    public Object getItem(int position) {
        return list_msg.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    ID id;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null || convertView.getTag() == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.listview_item, null);
            id = new ID();
            id.tv_left = (TextView) convertView.findViewById(R.id.me);
            id.tv_right = (TextView) convertView.findViewById(R.id.you);
            convertView.setTag(id);

        } else {
            id = (ID) convertView.getTag();
        }


        if (getItemViewType(position) == Msg.TYPE_RECIVE) {
            id.tv_right.setVisibility(View.VISIBLE);
            id.tv_left.setVisibility(View.GONE);
            id.tv_right.setText(list_msg.get(position).getContent());
        } else {
            id.tv_left.setVisibility(View.VISIBLE);
            id.tv_right.setVisibility(View.GONE);
            id.tv_left.setText(list_msg.get(position).getContent());
        }

        return convertView;
    }

    class ID{

        private TextView tv_left;
        private TextView tv_right;

    }

}
