package com.qwm.livedemo.daniu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatRadioButton;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.qwm.livedemo.BaseActivity;
import com.qwm.livedemo.R;

public class DaniuLiveActivity extends BaseActivity {

    private RadioGroup mAv_rg;
    private AppCompatRadioButton mRecord_rb;
    private Button mStartEnd_btn;
    private EditText mUrl_edt;
    private RadioGroup mWatermark_rg;
    private RadioGroup mRp_rg;
    private AppCompatRadioButton mVolume_rb;
    private RadioGroup mCode_rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daniu_live);
        initView();
    }

    private void initView() {
        mAv_rg = (RadioGroup)findViewById(R.id.av_rg);
        mWatermark_rg = (RadioGroup)findViewById(R.id.watermark_rg);
        mRp_rg = (RadioGroup)findViewById(R.id.rp_rg);
        mRecord_rb = (AppCompatRadioButton)findViewById(R.id.record_rb);
        mVolume_rb = (AppCompatRadioButton)findViewById(R.id.volume_rb);
        mCode_rg = (RadioGroup)findViewById(R.id.code_rg);
        mUrl_edt = (EditText)findViewById(R.id.url_edt);
        mStartEnd_btn = (Button)findViewById(R.id.start_end_btn);
    }
}
