package com.jephy.retrofitpracticedemo;

import android.util.Log;

import com.jephy.retrofitpracticedemo.web.FirmwareVersionModel;
import com.jephy.retrofitpracticedemo.web.FirmwareWebDao;

import java.lang.ref.SoftReference;
import java.util.List;

/**
 * Created by jfyang on 12/14/17.
 */

class FirmwareInfoPresenter {
    private static final String TAG = "FirmwareInfoPresenter";

    private SoftReference<FirmwareUpdateView> mViewReference;

    public FirmwareInfoPresenter(FirmwareUpdateView view) {
        mViewReference = new SoftReference<FirmwareUpdateView>(view);
    }

    public void fetchFirmwareInfo() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<FirmwareVersionModel> firmwareVersionModelList = FirmwareWebDao.requestFirmwareInfoSync();
                if (firmwareVersionModelList != null) {

                    FirmwareUpdateView view = mViewReference.get();
                    if (null != view) {
                        view.onUpdateInfoFetched(firmwareVersionModelList);
                    }

                    Log.d(TAG, "versionModelList = " + firmwareVersionModelList);
                }
            }
        }).start();
    }

}
