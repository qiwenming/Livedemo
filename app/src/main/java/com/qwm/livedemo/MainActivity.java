package com.qwm.livedemo;

import android.Manifest;
import android.os.Bundle;
import android.view.View;

import com.qwm.livedemo.daniu.DaniuLiveActivity;
import com.qwm.livedemo.listener.PermissionListener;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toDaNiu(View view){
        //首先申请权限
        requestPermission(new String[]{
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO
        }, 1005, new PermissionListener() {
            @Override
            public void granted(int requestCode, String[] permissions) {
                startActivity("大牛直播",DaniuLiveActivity.class);
            }
        });
    }
}
