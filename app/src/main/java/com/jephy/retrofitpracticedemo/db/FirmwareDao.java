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

    public static void saveOrUpdateFirmwareInfo(List<FirmwareVersionModel> firmwareVersionModelList) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.where(FirmwareDB.class).findAll().deleteAllFromRealm();
        realm.commitTransaction();

        realm.beginTransaction();
        for (FirmwareVersionModel firmwareVersionModel : firmwareVersionModelList) {
            FirmwareDB firmwareDB = new FirmwareDB();
            firmwareDB.setBaseline(firmwareVersionModel.getBaseline());
            firmwareDB.setLatestVersion(firmwareVersionModel.getLatestVersion());
            firmwareDB.setMd5(firmwareVersionModel.getMd5());
            firmwareDB.setProductnum(firmwareVersionModel.getProductnum());
            firmwareDB.setProductmodel(firmwareVersionModel.getProductmodel());
            firmwareDB.setSubproductmodel(firmwareVersionModel.getSubproductmodel());
            firmwareDB.setUrl(firmwareVersionModel.getUrl());
            List<String> msgs = firmwareVersionModel.getMsg();

            List<String> msg_ens = firmwareVersionModel.getMsg_en();

            List<String> msg_tws = firmwareVersionModel.getMsg_tw();

            for (String msgCN : msgs) {
                firmwareDB.getMsgCNList().add(new FeatureMessageItem(msgCN));
            }

            for (String msgTW: msg_tws){
                firmwareDB.getMsgTWList().add(new FeatureMessageItem(msgTW));
            }

            for (String msgEN : msg_ens) {
                firmwareDB.getMsgEnList().add(new FeatureMessageItem(msgEN));
            }

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
