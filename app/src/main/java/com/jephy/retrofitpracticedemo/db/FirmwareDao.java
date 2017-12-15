package com.jephy.retrofitpracticedemo.db;

import android.util.Log;


import com.jephy.retrofitpracticedemo.web.FirmwareVersionModel;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by jfyang on 12/15/17.
 */

public class FirmwareDao {
    private static String TAG = "FirmwareDao";

    public static void saveOrUpdateAllFirmwareInfo(List<FirmwareVersionModel> firmwareVersionModelList) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.where(FirmwareDB.class).findAll().deleteAllFromRealm();
        realm.commitTransaction();

        realm.beginTransaction();
        for (FirmwareVersionModel firmwareVersionModel : firmwareVersionModelList) {
            FirmwareDB firmwareDB = new FirmwareDB();
            firmwareDB.copyFieldFrom(firmwareVersionModel);
            realm.copyToRealm(firmwareDB);

        }
        realm.commitTransaction();

        RealmResults<FirmwareDB> firmwareDBS = realm.where(FirmwareDB.class).findAll();
        Log.d(TAG, "firmwareDBS.size() = " + firmwareDBS.size());
        for (FirmwareDB firmwareDB : firmwareDBS) {
            Log.d(TAG, "firmwareDB = " + firmwareDB.toString()+", messageCN = "+firmwareDB.getMsgCNList().toString());
        }

    }
}
