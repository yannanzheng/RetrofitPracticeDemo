package com.jephy.retrofitpracticedemo.db;

import io.realm.RealmObject;

/**
 * Created by jfyang on 12/15/17.
 */

public class FeatureMessageItem extends RealmObject {
    private String msg;

    public FeatureMessageItem() {
    }

    public FeatureMessageItem(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
