package com.jephy.retrofitpracticedemo.web;

import android.util.Log;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jfyang on 12/15/17.
 */

public class FirmwareWebDao {

    private static final String TAG = "FirmwareWebDao";

    public static List<FirmwareVersionModel> requestFirmwareInfoSync() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.77:100/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FirmwareUpdateService service = retrofit.create(FirmwareUpdateService.class);
        Call<AllUpdateInfoResponse> request = service.listRepos("1");
        try {
            Response<AllUpdateInfoResponse> response = request.execute();
            Log.d(TAG, "error = " + response.body().getError());
            Log.d(TAG, "versionModelList = " + response.body().getData());
            return response.body().getData();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

//    @NonNull
//    public \ void requestFirmwareInfoAsync() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://192.168.0.77:100/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        FirmwareUpdateService service = retrofit.create(FirmwareUpdateService.class);
//        Call<AllUpdateInfoResponse> repos = service.listRepos("1");
//        //                    Response<String> response = repos.execute();
//        repos.enqueue(new Callback<AllUpdateInfoResponse>() {
//            @Override
//            public void onResponse(Call<AllUpdateInfoResponse> call, Response<AllUpdateInfoResponse> response) {
//                Log.d(TAG, "response = " + response);
//                int error = response.body().getError();
//                List<FirmwareVersionModel> firmwareVersionModelList = response.body().getData();
//                FirmwareUpdateView view = mViewReference.get();
//                if (null != view) {
//                    view.onUpdateInfoFetched(firmwareVersionModelList);
//                }
//
//                Log.d(TAG, "error = " + error);
//                Log.d(TAG, "versionModelList = " + firmwareVersionModelList);
//
//
//                FirmwareDao.saveOrUpdateFirmwareInfo(firmwareVersionModelList);
//            }
//
//            @Override
//
//            public void onFailure(Call call, Throwable t) {
//                Log.d(TAG, "t = " + t);
//            }
//        });
//    }

}
