package com.jephy.retrofitpracticedemo;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

/**
 * Created by jfyang on 12/14/17.
 */
public class MyViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = "MyViewHolder";
    @BindView(R.id.device_model_name)
    TextView deviceModelName;

    @BindView(R.id.device_connect_state_tv)
    TextView deviceConntectState;

    @BindView(R.id.version)
    TextView version;

    @BindView(R.id.new_feature_container)
    LinearLayout newFeatureContainer;

    @BindView(R.id.show_more)
    CheckedTextView showMoreToggleButton;

    public MyViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @OnClick(R.id.download_firmware)
    public void download(View view) {
        Log.d(TAG, "点击下载条目: " + deviceModelName.getText().toString());
    }

    @OnClick(R.id.show_more)
    public void showMoreFeature(View view) {
        int adapterPosition = getAdapterPosition();
        if (showMoreToggleButton.isChecked()) {
            onSubItemClickListener.unFoldNewFeature(adapterPosition);
            showMoreToggleButton.setChecked(false);
            showMoreToggleButton.setText("收起");
        }else {
            onSubItemClickListener.foldNewFeature(adapterPosition);
            showMoreToggleButton.setChecked(true);
            showMoreToggleButton.setText("更多");
        }
    }

    private OnSubItemClickListener onSubItemClickListener;

    public void setOnSubItemClickListener(OnSubItemClickListener onSubItemClickListener) {
        this.onSubItemClickListener = onSubItemClickListener;
    }

    public interface OnSubItemClickListener{
        void unFoldNewFeature(int position);
        void foldNewFeature(int position);
    }

}
