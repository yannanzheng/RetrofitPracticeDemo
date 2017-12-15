package com.jephy.retrofitpracticedemo.db;

import com.jephy.retrofitpracticedemo.model.Firmware;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by jfyang on 12/15/17.
 */

public class FirmwareDB extends RealmObject {

    @PrimaryKey
    private String productnum;

    private String productmodel;

    private String subproductmodel;

    private int baseline;

    private String latestVersion;

    private String md5;

    /**
     * remote download url
     */
    private String url;

    /**
     * local store url
     */
    private String localUrl;

    private RealmList<FeatureMessageItem> msgEnList = new RealmList<>();
    private RealmList<FeatureMessageItem> msgCNList = new RealmList<>();
    private RealmList<FeatureMessageItem> msgTWList = new RealmList<>();

    public FirmwareDB() {
    }


    public String getProductnum() {
        return productnum;
    }

    public void setProductnum(String productnum) {
        this.productnum = productnum;
    }

    public String getProductmodel() {
        return productmodel;
    }

    public void setProductmodel(String productmodel) {
        this.productmodel = productmodel;
    }

    public String getSubproductmodel() {
        return subproductmodel;
    }

    public void setSubproductmodel(String subproductmodel) {
        this.subproductmodel = subproductmodel;
    }

    public int getBaseline() {
        return baseline;
    }

    public void setBaseline(int baseline) {
        this.baseline = baseline;
    }

    public String getLatestVersion() {
        return latestVersion;
    }

    public void setLatestVersion(String latestVersion) {
        this.latestVersion = latestVersion;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLocalUrl() {
        return localUrl;
    }

    public void setLocalUrl(String localUrl) {
        this.localUrl = localUrl;
    }

    public RealmList<FeatureMessageItem> getMsgEnList() {
        return msgEnList;
    }

    public void setMsgEnList(RealmList<FeatureMessageItem> msgEnList) {
        this.msgEnList = msgEnList;
    }

    public RealmList<FeatureMessageItem> getMsgCNList() {
        return msgCNList;
    }

    public void setMsgCNList(RealmList<FeatureMessageItem> msgCNList) {
        this.msgCNList = msgCNList;
    }

    public RealmList<FeatureMessageItem> getMsgTWList() {
        return msgTWList;
    }

    public void setMsgTWList(RealmList<FeatureMessageItem> msgTWList) {
        this.msgTWList = msgTWList;
    }

    @Override
    public String toString() {
        return "FirmwareDB{" +
                "productnum='" + productnum + '\'' +
                ", productmodel='" + productmodel + '\'' +
                ", subproductmodel='" + subproductmodel + '\'' +
                ", baseline=" + baseline +
                ", latestVersion='" + latestVersion + '\'' +
                ", md5='" + md5 + '\'' +
                ", url='" + url + '\'' +
                ", localUrl='" + localUrl + '\'' +
                ", msgEnList=" + msgEnList +
                ", msgCNList=" + msgCNList +
                ", msgTWList=" + msgTWList +
                '}';
    }

    public void copyFieldFrom(Firmware firmware) {
        setProductnum(firmware.getProductnum());
        setProductmodel(firmware.getProductmodel());
        setSubproductmodel(firmware.getSubproductmodel());
        setBaseline(firmware.getBaseline());
        setLatestVersion(firmware.getLatestVersion());
        setMd5(firmware.getMd5());
        setUrl(firmware.getUrl());

        List<String> msgStringList = firmware.getMsg();
        List<String> msgEnStringList = firmware.getMsg_en();
        List<String> msgTWStringList = firmware.getMsg_tw();
        addMessage(msgCNList,msgStringList);
        addMessage(msgEnList,msgEnStringList);
        addMessage(msgTWList,msgTWStringList);
    }

    private void addMessage(RealmList msgDBList, List<String> msgList) {
        for (String msg : msgList) {
            msgDBList.add(new FeatureMessageItem(msg));
        }
    }
}
