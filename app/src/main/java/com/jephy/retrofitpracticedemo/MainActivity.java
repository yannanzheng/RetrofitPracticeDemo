package com.jephy.retrofitpracticedemo;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MyMainActivity";
    private static final String requestURL = "http://192.168.0.77:100/index/updateInfoAll";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestFirmwareInfoAsync();
    }

    @NonNull
    private void requestFirmwareInfoAsync() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.77:100/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FirmwareUpdateService service = retrofit.create(FirmwareUpdateService.class);
        Call<AllUpdateInfoResponse> repos = service.listRepos("1");
        //                    Response<String> response = repos.execute();
        repos.enqueue(new Callback<AllUpdateInfoResponse>() {
            @Override
            public void onResponse(Call<AllUpdateInfoResponse> call, Response<AllUpdateInfoResponse> response) {
                Log.d(TAG, "response = " + response);
                int error = response.body().getError();
                List<AllUpdateInfoResponse.VersionModel> versionModelList = response.body().getData();

                Log.d(TAG, "error = " + error);
                Log.d(TAG, "versionModelList = " + versionModelList);
            }

            @Override

            public void onFailure(Call call, Throwable t) {
                Log.d(TAG, "t = " + t);
            }
        });
    }
}
