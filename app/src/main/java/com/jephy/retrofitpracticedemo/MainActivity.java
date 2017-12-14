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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
        public void onBindViewHolder(MyViewHolder holder, int position) {
            FirmwareVersionModel firmwareVersionModel = firmwareVersionModelList.get(position);
            holder.deviceModelName.setText(firmwareVersionModel.getProductmodel());
            holder.version.setText(firmwareVersionModel.getLatestVersion());
            List<String> msg = firmwareVersionModel.msg;
//            ArrayList<TextView> featureTextViews = new ArrayList<>();

            for (int i = 0; i < msg.size(); i++) {
                TextView textView = new TextView(mContext);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.cycle_dot_blue, 0, 0, 0);
                textView.setCompoundDrawablePadding(6);
//                featureTextViews.add(textView);
                holder.newFeatureContainer.addView(textView);
            }

//            holder.newFeatureContainer.addView();

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

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.device_model_name)
        TextView deviceModelName;

        @BindView(R.id.device_connect_state_tv)
        TextView deviceConntectState;

        @BindView(R.id.version)
        TextView version;

        @BindView(R.id.new_feature_container)
        LinearLayout newFeatureContainer;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.download_firmware)
        public void download(View view) {
            Log.d(TAG, "点击下载条目: " + deviceModelName.getText().toString());
        }

    }
}
