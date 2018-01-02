package com.jephy.retrofitpracticedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.jephy.retrofitpracticedemo.db.FirmwareDB;
import com.jephy.retrofitpracticedemo.model.Firmware;
import com.jephy.retrofitpracticedemo.util.FirmwareUtil;

import java.util.List;

public class MainActivity extends AppCompatActivity implements FirmwareUpdateView{
    private static final String TAG = "MyMainActivity";
    private static final String TAG_Version_Compare = "VersionCompare";
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

//        testFirmwareCompare();

//        String a = "abc%sd";
//        String formatA = String.format(a, "mmmm");
//        Log.e(TAG_Version_Compare, "formatA = "+formatA);


    }

    private void testFirmwareCompare() {
        Log.i(TAG_Version_Compare, "2.2.1.1>2.2.1.0, assert true: " + FirmwareUtil.compareVersion("2.2.1.1", "2.2.1.0"));
        Log.i(TAG_Version_Compare, "2.2.1.1>2.2.1, assert true: " + FirmwareUtil.compareVersion("2.2.1.1", "2.2.1"));
        Log.i(TAG_Version_Compare, "2.3.1.1>2.2.1.1, assert true: " + FirmwareUtil.compareVersion("2.3.1.1", "2.2.1.1"));
        Log.i(TAG_Version_Compare, "3.2.1.1>2.2.1.1, assert true: " + FirmwareUtil.compareVersion("3.2.1.1", "2.2.1.1"));
        Log.i(TAG_Version_Compare, "2.2.2.1>2.2.1.1, assert true: " + FirmwareUtil.compareVersion("2.2.2.1", "2.2.1.1"));
        Log.i(TAG_Version_Compare, "2.2.2.1>2.2.1.1, assert true: " + FirmwareUtil.compareVersion("2.2.2.1", "2.2.1.1"));
        Log.i(TAG_Version_Compare, "2.2.2>2.2.1.1, assert true: " +FirmwareUtil.compareVersion("2.2.2", "2.2.1.1"));
        Log.i(TAG_Version_Compare, "2.2.2>2.2.1.9, assert true: " + FirmwareUtil.compareVersion("2.2.2", "2.2.1.0"));
        Log.i(TAG_Version_Compare, "2.2.2>2.2.1, assert true: " + FirmwareUtil.compareVersion("2.2.2", "2.2.1"));
    }

    @Override
    public void onUpdateInfoFetched(final List<Firmware> firmwareDBList) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mAdapter.setNewData(firmwareDBList);
                mAdapter.notifyDataSetChanged();
            }
        });

    }

}
