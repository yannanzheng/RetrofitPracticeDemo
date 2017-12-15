package com.jephy.retrofitpracticedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jephy.retrofitpracticedemo.db.FirmwareDB;

import java.util.List;

public class MainActivity extends AppCompatActivity implements FirmwareUpdateView{
    private static final String TAG = "MyMainActivity";
    private RecyclerView recyclerView;

    private FirmwareInfoPresenter firmwareInfoPresenter = new FirmwareInfoPresenter(MainActivity.this,this);
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_list_view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new MyAdapter(this, MainActivity.this);
        recyclerView.setAdapter(mAdapter);

        firmwareInfoPresenter.fetchFirmwareInfo();
    }

    @Override
    public void onUpdateInfoFetched(final List<FirmwareDB> firmwareDBList) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mAdapter.setNewData(firmwareDBList);
                mAdapter.notifyDataSetChanged();
            }
        });

    }

}
