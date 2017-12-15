package com.jephy.retrofitpracticedemo.db;

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

    private RealmList<String> msgEnList = new RealmList<>();
    private RealmList<String> msgCNList = new RealmList<>();
    private RealmList<String> msgTWList = new RealmList<>();

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

    public RealmList<String> getMsgEnList() {
        return msgEnList;
    }

    public void setMsgEnList(RealmList<String> msgEnList) {
        this.msgEnList = msgEnList;
    }

    public RealmList<String> getMsgCNList() {
        return msgCNList;
    }

    public void setMsgCNList(RealmList<String> msgCNList) {
        this.msgCNList = msgCNList;
    }

    public RealmList<String> getMsgTWList() {
        return msgTWList;
    }

    public void setMsgTWList(RealmList<String> msgTWList) {
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
}
