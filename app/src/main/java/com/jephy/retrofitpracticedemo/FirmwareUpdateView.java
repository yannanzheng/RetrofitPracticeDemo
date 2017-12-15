package com.jephy.retrofitpracticedemo;

import com.jephy.retrofitpracticedemo.db.FirmwareDB;

import java.util.List;

/**
 * Created by jfyang on 12/14/17.
 */

interface FirmwareUpdateView {
    void onUpdateInfoFetched(List<FirmwareDB> firmwareDBList);
}
