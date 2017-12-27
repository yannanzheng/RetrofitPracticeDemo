package com.jephy.retrofitpracticedemo.db;

import android.util.Log;


import com.jephy.retrofitpracticedemo.model.Firmware;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by jfyang on 12/15/17.
 */

public class FirmwareDao {
    private static String TAG = "FirmwareDao";

    public static void saveOrUpdateAllFirmwareInfo(List<Firmware> firmwareList) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.where(FirmwareDB.class).findAll().deleteAllFromRealm();
        realm.commitTransaction();

        realm.beginTransaction();
        for (Firmware firmware : firmwareList) {
            FirmwareDB firmwareDB = new FirmwareDB();
            firmwareDB.copyFieldFrom(firmware);
            realm.copyToRealm(firmwareDB);

        }
        realm.commitTransaction();

        RealmResults<FirmwareDB> firmwareDBS = realm.where(FirmwareDB.class).findAll();
        Log.d(TAG, "firmwareDBS.size() = " + firmwareDBS.size());
        for (FirmwareDB firmwareDB : firmwareDBS) {
            Log.d(TAG, "firmwareDB = " + firmwareDB.toString()+", messageCN = "+firmwareDB.getMsgCNList().toString());
        }

    }

    public static List<Firmware> findAll() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<FirmwareDB> firmwareDBList = realm.where(FirmwareDB.class).findAll();
        if (firmwareDBList != null) {
            List<Firmware> firmwareVersionModelList = new ArrayList<>();
            for (FirmwareDB firmwareDB:firmwareDBList){
                Firmware firmware = new Firmware().copyFieldFrom(firmwareDB);
                firmwareVersionModelList.add(firmware);
            }
            return firmwareVersionModelList;
        }

        return null;
    }
}
