package com.jephy.retrofitpracticedemo;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
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

//        normalRequest();
        retrofitRequest();

    }


    @NonNull
    private void retrofitRequest() {
         new Thread(new Runnable() {
            @Override
            public void run() {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://192.168.0.77:100/")
                        .addConverterFactory(GsonConverterFactory.create() )
                        .build();

                GitHubService service = retrofit.create(GitHubService.class);
                Call<MyResponse> repos = service.listRepos("1");
                //                    Response<String> response = repos.execute();
                repos.enqueue(new Callback<MyResponse>() {
                    @Override
                    public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {
                        Log.d(TAG,"response = "+response);
                        int error = response.body().getError();
                        List<MyResponse.VersionModel> versionModelList = response.body().getData();

                        Log.d(TAG,"error = "+error);
                        Log.d(TAG, "versionModelList = " + versionModelList);
                    }

                    @Override

                    public void onFailure(Call call, Throwable t) {
                        Log.d(TAG,"t = "+t);
                    }
                });

                //                Log.d(TAG,"repos = "+repos.toString() );
            }
        }).start();
    }


    private void normalRequest() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                URL url = null;
                try {
                    url = new URL(requestURL);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setRequestProperty("type", "1");
                    int responseCode = httpURLConnection.getResponseCode();
                    Log.d(TAG, "responseCode = " + responseCode);
                    InputStream inputStream = httpURLConnection.getInputStream();
                    byte[] data = new byte[10000];
                    int read = inputStream.read(data);
                    Log.d(TAG, "read.lenght = " + read);

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
}
