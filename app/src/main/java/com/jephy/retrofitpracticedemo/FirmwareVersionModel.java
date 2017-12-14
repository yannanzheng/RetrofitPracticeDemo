package com.jephy.retrofitpracticedemo;

import java.util.List;

/**
 * Created by jfyang on 12/14/17.
 */
class FirmwareVersionModel {

    String productnum;
    String productmodel;
    String subproductmodel;
    int baseline;
    String latestVersion;
    String url;
    List<String> msg;
    List<String> msg_tw;
    List<String> msg_en;
    String md5;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getMsg() {
        return msg;
    }

    public void setMsg(List<String> msg) {
        this.msg = msg;
    }

    public List<String> getMsg_tw() {
        return msg_tw;
    }

    public void setMsg_tw(List<String> msg_tw) {
        this.msg_tw = msg_tw;
    }

    public List<String> getMsg_en() {
        return msg_en;
    }

    public void setMsg_en(List<String> msg_en) {
        this.msg_en = msg_en;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    @Override
    public String toString() {
        return "VersionModel{" +
                "productnum='" + productnum + '\'' +
                ", productmodel='" + productmodel + '\'' +
                ", subproductmodel='" + subproductmodel + '\'' +
                ", baseline=" + baseline +
                ", latestVersion='" + latestVersion + '\'' +
                ", url='" + url + '\'' +
                ", msg=" + msg +
                ", msg_tw=" + msg_tw +
                ", msg_en=" + msg_en +
                ", md5='" + md5 + '\'' +
                '}';
    }
}
