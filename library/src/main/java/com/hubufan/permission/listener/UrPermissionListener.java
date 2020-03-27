package com.hubufan.permission.listener;

/**
 * @author With You
 * @version 1.0.0
 * @date 2020/2/25 22:14
 * @email 1713397546@qq.com
 * @description
 */
public interface UrPermissionListener {

    /**
     * 请求权限成功回调
     */
    void requestPermissionsSuccess();

    /**
     * 请求权限失败回调
     */
    void requestPermissionsFail();

}
