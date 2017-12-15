package com.jephy.retrofitpracticedemo.web;

import com.jephy.retrofitpracticedemo.model.Firmware;

import java.util.List;

/**
 * Created by jfyang on 12/14/17.
 */

public class FirmwareUpgradeResponse {
    int error;
    List<Firmware> data;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public List<Firmware> getData() {
        return data;
    }

    public void setData(List<Firmware> data) {
        this.data = data;
    }

}
