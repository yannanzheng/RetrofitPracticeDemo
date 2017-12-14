package com.jephy.retrofitpracticedemo;

import java.util.List;

/**
 * Created by jfyang on 12/14/17.
 */

public class AllUpdateInfoResponse {
    int error;
    List<FirmwareVersionModel> data;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public List<FirmwareVersionModel> getData() {
        return data;
    }

    public void setData(List<FirmwareVersionModel> data) {
        this.data = data;
    }

}
