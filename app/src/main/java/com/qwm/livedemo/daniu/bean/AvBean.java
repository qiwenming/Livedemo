package com.qwm.livedemo.daniu.bean;

import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * <b>Project:</b> Livedemo<br>
 * <b>Create Date:</b> 2017/4/14<br>
 * <b>Author:</b> qiwenming<br>
 * <b>Description:</b>
 * 存储影视频选择的
 * <br>
 */
public class AvBean implements Serializable{

    public AvBean(int audio_opt, int video_opt) {
        this.audio_opt = audio_opt;
        this.video_opt = video_opt;
    }

    //0：不发布 audio；
    //1：发布 audio；
    //2：对接外部编码后的 audio 数据（AAC）
    public int audio_opt;

    //0：不发布 video；
    //1：发布 video；
    //2：对接外部编码后的 video 数据（H.264）
    public int video_opt;

    public static AvBean getAvBeanByType(AvBeanType type){
        if(type==AvBeanType.AUDIO){
            return  new AvBean(2,0);
        }else if(type==AvBeanType.VIDEO){
            return new AvBean(0,2);
        }
        return new AvBean(2,2);
    }

    public enum AvBeanType{
        AUDIO,
        VIDEO,
        AV
    }
}
