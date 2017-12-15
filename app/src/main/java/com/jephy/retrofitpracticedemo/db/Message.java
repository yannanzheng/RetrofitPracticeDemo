package com.jephy.retrofitpracticedemo.db;

import io.realm.RealmObject;

/**
 * Created by jfyang on 12/15/17.
 */

public class Message extends RealmObject {
    private static final int LANGUAGE_EN = 0;
    private static final int LANGUAGE_CN = 1;
    private static final int LANGUAGE_TW = 2;

    private int languageType;

    private  String featureMessage;

    public Message(int languageType, String featureMessage) {
        this.languageType = languageType;
        this.featureMessage = featureMessage;
    }

    public Message() {
    }

    public int getLanguageType() {
        return languageType;
    }

    public void setLanguageType(int languageType) {
        this.languageType = languageType;
    }

    public String getFeatureMessage() {
        return featureMessage;
    }

    public void setFeatureMessage(String featureMessage) {
        this.featureMessage = featureMessage;
    }
}
