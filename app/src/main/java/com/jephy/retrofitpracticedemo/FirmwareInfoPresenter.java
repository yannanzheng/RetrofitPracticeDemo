package com.jephy.retrofitpracticedemo;

import android.content.Context;
import android.util.Log;

import com.jephy.retrofitpracticedemo.db.FirmwareDB;
import com.jephy.retrofitpracticedemo.db.FirmwareDao;
import com.jephy.retrofitpracticedemo.util.InternetUtils;
import com.jephy.retrofitpracticedemo.model.Firmware;
import com.jephy.retrofitpracticedemo.web.FirmwareWebDao;

import java.lang.ref.SoftReference;
import java.util.List;

/**
 * Created by jfyang on 12/14/17.
 */

class FirmwareInfoPresenter {
    private static final String TAG = "FirmwareInfoPresenter";

    private SoftReference<FirmwareUpdateView> mViewReference;
    private Context mContext;

    public FirmwareInfoPresenter(Context context,FirmwareUpdateView view) {
        this.mContext = context;
        mViewReference = new SoftReference<FirmwareUpdateView>(view);
    }

    public void fetchFirmwareInfo() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean isNetworkAvaiable = InternetUtils.hasEvomotionConnection(mContext);
                Log.d(TAG, "check network, isNetworkAvaiable = " + isNetworkAvaiable);

                if (isNetworkAvaiable) {
                    List<Firmware> firmwareList = FirmwareWebDao.requestFirmwareInfoSync();
                    if (firmwareList != null) {

                        FirmwareDao.saveOrUpdateAllFirmwareInfo(firmwareList);

                        List<Firmware> firmwares = FirmwareDao.findAll();

                        Log.d(TAG, "firmwares = " + firmwares);
                        FirmwareUpdateView view = mViewReference.get();
                        if (null != view) {
                            view.onUpdateInfoFetched(firmwares);
                        }

                        Log.d(TAG, "versionModelList = " + firmwareList);
                    }
                }
            }
        }).start();
    }

}
