package com.qwm.livedemo.daniu.bean;

import java.io.Serializable;

/**
 * <b>Project:</b> Livedemo<br>
 * <b>Create Date:</b> 2017/4/14<br>
 * <b>Author:</b> qiwenming<br>
 * <b>Description:</b> 分辨率bean <br>
 */
public class RpBean implements Serializable {
    public int width = 640;
    public int  height = 480;

    public static RpBean getRpBeanByType(RpBeanType type){
        RpBean rp = new  RpBean();
        if(type==RpBeanType.XH){
            rp.width = 1280;
            rp.height = 720;
        }else  if(type==RpBeanType.H){
            rp.width = 640;
            rp.height = 480;
        }else  if(type==RpBeanType.M){
            rp.width = 320;
            rp.height = 240;
        }else if(type==RpBeanType.L){
            rp.width = 176;
            rp.height = 144;
        }
        return rp;
    }

    public enum RpBeanType{
        XH,H,M,L
    }
}
