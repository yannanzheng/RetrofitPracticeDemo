package com.jephy.retrofitpracticedemo;

import com.jephy.retrofitpracticedemo.model.Firmware;

import java.util.List;

/**
 * Created by jfyang on 12/14/17.
 */

interface FirmwareUpdateView {
    void onUpdateInfoFetched(List<Firmware> firmwareList);
}
