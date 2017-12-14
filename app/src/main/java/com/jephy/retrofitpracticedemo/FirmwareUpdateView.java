package com.jephy.retrofitpracticedemo;

import java.util.List;

/**
 * Created by jfyang on 12/14/17.
 */

interface FirmwareUpdateView {
    void onUpdateInfoFetched(List<FirmwareVersionModel> firmwareVersionModelList);
}
