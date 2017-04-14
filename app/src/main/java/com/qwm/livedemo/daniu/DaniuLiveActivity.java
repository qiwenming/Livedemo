package com.qwm.livedemo.daniu;

import android.os.Bundle;
import android.support.v7.widget.AppCompatRadioButton;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.daniulive.smartpublisher.SmartPublisherJni;
import com.qwm.livedemo.BaseActivity;
import com.qwm.livedemo.R;
import com.qwm.livedemo.daniu.bean.AvBean;
import com.qwm.livedemo.daniu.bean.RpBean;

public class DaniuLiveActivity extends BaseActivity {

    private RadioGroup mAv_rg;
    private AppCompatRadioButton mRecord_rb;
    private Button mStartEnd_btn;
    private EditText mUrl_edt;
    private RadioGroup mWatermark_rg;
    private RadioGroup mRp_rg;
    private AppCompatRadioButton mVolume_rb;
    private RadioGroup mCode_rg;
    private static final String TAG = "DaniuLiveActivity";

    static {
        System.loadLibrary("SmartPublisher");
    }

    private SmartPublisherJni libPublisher;
    private String mWaterText = "小明测试";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daniu_live);
        initView();
        initEvent();
        initPublish();
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

    private void initEvent() {
       mStartEnd_btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if("开始推流".equals(mStartEnd_btn.getText().toString().trim())){
//                   startPulish();
                   testp();
               }else{
                   endPulish();
               }
           }
       });
    }

    private void initPublish() {
        libPublisher = new SmartPublisherJni();
    }

    private void startPulish() {
        //获取参数
        AvBean av = getSelectAv();
        RpBean rp = getSelectRp();

        //1.初始化
        libPublisher.SmartPublisherInit(this,av.audio_opt,av.video_opt,rp.width,rp.height);

        //2.事件回调
//        libPublisher.SetSmartPublisherEventCallback(null);

        //3.硬编码
        if(mCode_rg.getCheckedRadioButtonId()==R.id.code_hard_rb){
            int support = libPublisher.SetSmartPublisherVideoHWEncoder( getHardwareEncoderKbps(rp) );
            Toast.makeText(this,support==0?"支持硬编码":"不支持硬编码",Toast.LENGTH_SHORT).show();
        }

        //4.水印设置
        int waterM = getSelectWaterMark();
        if( (waterM|DaNiuConstant.WATERMARK_IMG) == DaNiuConstant.WATERMARK_IMG){//图片
//            libPublisher.SmartPublisherSetPictureWatermark()
        }
        if( (waterM|DaNiuConstant.WATERMARK_TEXT) == DaNiuConstant.WATERMARK_TEXT){//文字
            libPublisher.SmartPublisherSetFontWatermark(mWaterText,1, SmartPublisherJni.WATERMARK.WATERMARK_FONTSIZE_BIG, SmartPublisherJni.WATERMARK.WATERMARK_POSITION_BOTTOMRIGHT,10,10);
        }

        //5.静音设置
//        libPublisher.SmartPublisherSetMute(mVolume_rb.isChecked()?1:0);


        //7.设置 rtmp publisher 类型
//        libPublisher.SetRtmpPublishingType(0);

        //6.录像本地
        libPublisher.SmartPublisherSetRecorder(0);

        //流地址
        String pushUrl = mUrl_edt.getText().toString().trim();
        libPublisher.SmartPublisherSetURL(pushUrl);
        libPublisher.SmartPublisherStart();
//        libPublisher.SmartPublisherOnCaptureVideoData()
    }

    private void endPulish() {

    }

    public void testp(){
        //获取参数
        AvBean av = getSelectAv();
        RpBean rp = getSelectRp();

        //1.初始化
        libPublisher.SmartPublisherInit(this,av.audio_opt,av.video_opt,rp.width,rp.height);
        //6.录像本地
        libPublisher.SmartPublisherSetRecorder(0);

        //流地址
        String pushUrl = mUrl_edt.getText().toString().trim();
        libPublisher.SmartPublisherSetURL(pushUrl);
        libPublisher.SmartPublisherStart();

    }

    /**
     * 获取 音视频
     * @return
     */
    public AvBean getSelectAv(){
        switch (mAv_rg.getCheckedRadioButtonId()) {
            case R.id.av_a_rb:
                return AvBean.getAvBeanByType(AvBean.AvBeanType.AUDIO);
            case R.id.av_v_rb:
                return AvBean.getAvBeanByType(AvBean.AvBeanType.VIDEO);
            case R.id.av_all_rb:
                return  AvBean.getAvBeanByType(AvBean.AvBeanType.AV);
        }
        return AvBean.getAvBeanByType(AvBean.AvBeanType.AV);
    }

    /**
     * 获取水印
     * @return
     */
    public int getSelectWaterMark(){
        switch (mWatermark_rg.getCheckedRadioButtonId()) {
            case R.id.watermark_all_rb:
                return DaNiuConstant.WATERMARK_ALL;
            case R.id.watermark_img_rb:
                return DaNiuConstant.WATERMARK_IMG;
            case R.id.watermark_text_rb:
                return DaNiuConstant.WATERMARK_TEXT;
            case R.id.watermark_none_rb:
                return DaNiuConstant.WATERMARK_NONE;
        }
        return  DaNiuConstant.WATERMARK_ALL;
    }

    /**
     * 获取分辨率
     * @return
     */
    public RpBean getSelectRp(){
        switch (mRp_rg.getCheckedRadioButtonId()) {
            case R.id.rp_xh_rb:
                return RpBean.getRpBeanByType(RpBean.RpBeanType.XH);
            case R.id.rp_h_rb:
                return RpBean.getRpBeanByType(RpBean.RpBeanType.H);
            case R.id.rp_m_rb:
                return RpBean.getRpBeanByType(RpBean.RpBeanType.M);
            case R.id.rp_l_rb:
                return RpBean.getRpBeanByType(RpBean.RpBeanType.L);
        }
        return RpBean.getRpBeanByType(RpBean.RpBeanType.H);
    }

    private int getHardwareEncoderKbps(RpBean rp) {
        int hwEncoderKpbs = 0;

        switch (rp.width) {
            case 176:
                hwEncoderKpbs = 300;
                break;
            case 320:
                hwEncoderKpbs = 500;
                break;
            case 640:
                hwEncoderKpbs = 1000;
                break;
            case 1280:
                hwEncoderKpbs = 1700;
                break;
            default:
                hwEncoderKpbs = 1000;
        }

        return hwEncoderKpbs;
    }

}
