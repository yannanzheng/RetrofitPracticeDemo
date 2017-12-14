package com.jephy.retrofitpracticedemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MyMainActivity";
    private static final String requestURL = "http://192.168.0.77:100/index/updateInfoAll";
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        requestFirmwareInfoAsync();
        recyclerView = findViewById(R.id.recycler_list_view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new MyAdapter(MainActivity.this));

        requestFirmwareInfoSync();
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
                List<FirmwareVersionModel> firmwareVersionModelList = response.body().getData();

                Log.d(TAG, "error = " + error);
                Log.d(TAG, "versionModelList = " + firmwareVersionModelList);
            }

            @Override

            public void onFailure(Call call, Throwable t) {
                Log.d(TAG, "t = " + t);
            }
        });
    }

    private void requestFirmwareInfoSync() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://192.168.0.77:100/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                FirmwareUpdateService service = retrofit.create(FirmwareUpdateService.class);
                Call<AllUpdateInfoResponse> request = service.listRepos("1");
                //                    Response<String> response = repos.execute();

                try {
                    Response<AllUpdateInfoResponse> response = request.execute();
                    Log.d(TAG, "error = " + response.body().getError());
                    Log.d(TAG, "versionModelList = " + response.body().getData());

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        private  LayoutInflater mLayoutInflater;
        private Context mContext;
        private List<FirmwareVersionModel> firmwareVersionModelList;

        public MyAdapter(Context mContext) {
            this.mContext = mContext;
            this.mLayoutInflater = LayoutInflater.from(mContext);
            this.firmwareVersionModelList = firmwareVersionModelList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.firmware_info_item,parent,false);
            MyViewHolder myViewHolder = new MyViewHolder(view);

            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 2;
        }
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {


        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
