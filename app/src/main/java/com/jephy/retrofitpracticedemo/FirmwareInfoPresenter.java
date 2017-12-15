package com.jephy.retrofitpracticedemo;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.jephy.retrofitpracticedemo.db.FirmwareDB;
import com.jephy.retrofitpracticedemo.db.FirmwareDao;
import com.jephy.retrofitpracticedemo.util.InternetUtils;
import com.jephy.retrofitpracticedemo.web.FirmwareVersionModel;
import com.jephy.retrofitpracticedemo.web.FirmwareWebDao;

import java.lang.ref.SoftReference;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

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
                    List<FirmwareVersionModel> firmwareVersionModelList = FirmwareWebDao.requestFirmwareInfoSync();
                    if (firmwareVersionModelList != null) {

                        FirmwareDao.saveOrUpdateAllFirmwareInfo(firmwareVersionModelList);
//                        for (FirmwareVersionModel firmwareVersionModel : firmwareVersionModelList) {
//                            FirmwareDB firmwareDB = new FirmwareDB();
//                            firmwareDB.copyFieldFrom(firmwareVersionModel);
//                        }
//
//                        Realm realm = Realm.getDefaultInstance();
//                        RealmResults<FirmwareDB> firmwareDBS = realm.where(FirmwareDB.class).findAll();
//                        for (){
//
//                        }


                        FirmwareUpdateView view = mViewReference.get();
                        if (null != view) {
                            view.onUpdateInfoFetched(firmwareVersionModelList);
                        }

                        Log.d(TAG, "versionModelList = " + firmwareVersionModelList);
                    }
                }
            }
        }).start();
    }

}
