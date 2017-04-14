package com.qwm.livedemo.listener;

/**
 * <b>Project:</b> YuantaiApplication<br>
 * <b>Create Date:</b> 2017/3/23<br>
 * <b>Author:</b> qiwenming<br>
 * <b>Description:</b> <br>
 *     动态权限申请回调类
 */
public abstract class PermissionListener {
    /**
     * 通过
     *
     * @param permissions
     */
    public abstract void granted(int requestCode, String[] permissions);


    /**
     * 拒绝
     * @param requestCode
     * @param deniedpermissions
     * @param permissions
     */
    public void denied(int requestCode, String[] deniedpermissions, String[] permissions){}

    /**
     * 设置权限结束，需要重新申请一次
     */
    public void onToRequestAggin(){}
    /**
     * 点击了弹框的 取消按钮
     */
    public void onCancel(){}
}

