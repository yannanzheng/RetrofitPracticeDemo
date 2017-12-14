package com.jephy.retrofitpracticedemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements FirmwareUpdateView{
    private static final String TAG = "MyMainActivity";
    private RecyclerView recyclerView;

    private FirmwareInfoPresenter firmwareInfoPresenter = new FirmwareInfoPresenter(this);
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_list_view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new MyAdapter(MainActivity.this);
        recyclerView.setAdapter(mAdapter);

        firmwareInfoPresenter.fetchFirmwareInfo();
    }

    @Override
    public void onUpdateInfoFetched(List<FirmwareVersionModel> firmwareVersionModelList) {
        mAdapter.setNewData(firmwareVersionModelList);
        mAdapter.notifyDataSetChanged();
    }

    private class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        private  LayoutInflater mLayoutInflater;
        private Context mContext;
        private List<FirmwareVersionModel> firmwareVersionModelList;

        public MyAdapter(Context mContext) {
            this.mContext = mContext;
            this.mLayoutInflater = LayoutInflater.from(mContext);
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.firmware_info_item,parent,false);
            MyViewHolder myViewHolder = new MyViewHolder(view);

            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position) {
            FirmwareVersionModel firmwareVersionModel = firmwareVersionModelList.get(position);
            holder.deviceModelName.setText(firmwareVersionModel.getProductmodel());
            holder.version.setText(firmwareVersionModel.getLatestVersion());
            final List<String> msg = firmwareVersionModel.msg;

            showNewFeatureItems(holder, msg, true);
            holder.setOnSubItemClickListener(new MyViewHolder.OnSubItemClickListener() {
                @Override
                public void unFoldNewFeature(int position) {
                    Log.d(TAG, "点击展开更多条目: " + position);
                    showNewFeatureItems(holder,msg,false);
                }

                @Override
                public void foldNewFeature(int position) {
                    showNewFeatureItems(holder,msg,true);
                }
            });
        }

        private void showNewFeatureItems(MyViewHolder holder, List<String> msg, boolean shouldFold) {
            int showNewFeatureItemNumber = msg.size();
            if (shouldFold && showNewFeatureItemNumber > 2) {
                showNewFeatureItemNumber = 2;
            }

            holder.newFeatureContainer.removeAllViews();

            for (int i = 0; i < showNewFeatureItemNumber; i++) {
                TextView textView = new TextView(mContext);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.cycle_dot_blue, 0, 0, 0);
                textView.setCompoundDrawablePadding(6);
                holder.newFeatureContainer.addView(textView);
            }
        }

        @Override
        public int getItemCount() {
            if (null != firmwareVersionModelList) {
                return firmwareVersionModelList.size();
            }
            return 0;
        }

        public void setNewData(List<FirmwareVersionModel> newData) {
            this.firmwareVersionModelList = newData;
        }
    }

}
