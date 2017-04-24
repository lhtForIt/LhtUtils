package com.lht.utils.lhtutils.Activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.lht.utils.lhtutils.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lht on 2017/1/23.
 */

public class Chat extends BaseActivity {


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
        EMClient.getInstance().chatManager().addMessageListener(msgListener);
        initMsgs();

        lv = (ListView) findViewById(R.id.lv);
        et = (EditText) findViewById(R.id.et);
        bt = (Button) findViewById(R.id.bt);

        myAdapter = new ChatAdapter(Chat.this, list);
        lv.setAdapter(myAdapter);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = et.getText().toString();
                if (!"".equals(content)) {
                    EMMessage message = EMMessage.createTxtSendMessage(content, "583067");
                    //如果是群聊，设置chattype，默认是单聊
//                    if (EMMessage.ChatType == CHATTYPE_GROUP)
//                        message.setChatType(EMMessage.ChatType.GroupChat);
                    message.setChatType(EMMessage.ChatType.Chat);
                    EMClient.getInstance().chatManager().sendMessage(message);
                    Msg msg_new = new Msg(Msg.TYPE_SEND, content);
                    list.add(msg_new);
                    myAdapter.notifyDataSetChanged();
//                    lv.setSelection(list.size()-1);
                    et.setText("");
                }
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(lv.getWindowToken(), 0);
            }
        });

    }

    private void initMsgs() {
        Msg msg1 = new Msg(Msg.TYPE_RECIVE, "你好");
        Msg msg2 = new Msg(Msg.TYPE_SEND, "有事么");
        Msg msg3 = new Msg(Msg.TYPE_RECIVE, "请问你是XXX么");
        list.add(msg1);
        list.add(msg2);
        list.add(msg3);

    }

    EMMessageListener msgListener = new EMMessageListener() {

        @Override
        public void onMessageReceived(final List<EMMessage> messages) {
            //收到消息

            Intent in = new Intent(getApplicationContext(), Chat.class);
            PendingIntent pi = PendingIntent.getActivity(Chat.this, 0, in, PendingIntent.FLAG_UPDATE_CURRENT);
            Log.d("lht", "====================messages:" + messages.get(0).getBody());
            Log.d("lht", "====================message_from:" + messages.get(0).getFrom());
            Log.d("lht", "====================message_username:" + messages.get(0).getUserName());
            Log.d("lht", "====================message_to:" + messages.get(0).getTo());
            String content = null;
            for (int i = 0; i < messages.size(); i++) {
                Log.d("lht", "====================进来了");
                String[] s = messages.get(i).getBody().toString().split(":");
                content = s[1].substring(1, s[1].length() - 1);
                Log.d("lht", "====================通知进来了");
                NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                Notification.Builder nf = new Notification.Builder(Chat.this)
                        .setTicker(messages.get(0).getFrom() + "发来一条消息")
                        .setSmallIcon(R.mipmap.image9)
                        .setWhen(System.currentTimeMillis())
                        .setAutoCancel(true)
                        .setContentTitle("MychatMessage")
                        .setContentText(content)
                        .setContentIntent(pi)
                        .setDefaults(Notification.DEFAULT_VIBRATE);
                Notification nof = nf.build();
                nm.notify(0, nof);
                Log.d("lht", "====================通知出去了");
                Log.d("lht", "====================出去了");
                Msg msg_new = new Msg(Msg.TYPE_RECIVE, content);
                list.add(msg_new);
                myAdapter.notifyDataSetChanged();

            }



        }

        @Override
        public void onCmdMessageReceived(List<EMMessage> messages) {
            //收到透传消息
            Log.d("lht", "====================messages:" + messages);
        }

        @Override
        public void onMessageRead(List<EMMessage> messages) {
            //收到已读回执
            Log.d("lht", "====================messages:" + messages);
        }

        @Override
        public void onMessageDelivered(List<EMMessage> message) {
            //收到已送达回执
            Log.d("lht", "====================messages:" + message);
        }

        @Override
        public void onMessageChanged(EMMessage message, Object change) {
            //消息状态变动
            Log.d("lht", "====================messages:" + change);
        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EMClient.getInstance().chatManager().removeMessageListener(msgListener);
    }
}
