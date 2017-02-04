package com.lht.utils.lhtutils.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.lht.utils.lhtutils.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lht on 2017/1/23.
 */

public class Chat extends BaseActivity{


    private ListView lv;
    private EditText et;
    private Button bt;
    private List<Msg> list = new ArrayList<>();
    private ChatAdapter myAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_activity);
        initMsgs();

        lv = (ListView) findViewById(R.id.lv);
        et = (EditText) findViewById(R.id.et);
        bt = (Button) findViewById(R.id.bt);

        myAdapter = new ChatAdapter(Chat.this,list);
        lv.setAdapter(myAdapter);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = et.getText().toString();
                if (!"".equals(content)) {
                    Msg msg_new = new Msg(Msg.TYPE_SEND, content);
                    list.add(msg_new);
                    myAdapter.notifyDataSetChanged();
                    lv.setSelection(list.size());
                    et.setText("");
                }
            }
        });
    }

    private void initMsgs() {
        Msg msg1 = new Msg( Msg.TYPE_RECIVE,"你好");
        Msg msg2= new Msg( Msg.TYPE_SEND,"有事么");
        Msg msg3= new Msg( Msg.TYPE_RECIVE,"请问你是XXX么");
        list.add(msg1);
        list.add(msg2);
        list.add(msg3);

    }
}
