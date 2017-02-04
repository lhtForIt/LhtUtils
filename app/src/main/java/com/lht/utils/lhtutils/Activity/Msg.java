package com.lht.utils.lhtutils.Activity;

/**
 * Created by lht on 2017/1/23.
 */

public class Msg {

    private int type;
    private String content;
    public static final int TYPE_RECIVE = 0;
    public static final int TYPE_SEND = 1;

    public Msg(int type, String content) {
        this.type = type;
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public String getContent() {
        return content;
    }
}
