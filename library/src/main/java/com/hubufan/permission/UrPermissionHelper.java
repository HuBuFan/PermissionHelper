package com.hubufan.permission;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;


import androidx.annotation.NonNull;

import com.hubufan.permission.listener.UrPermissionListener;
import com.hubufan.permission.util.UrPermissionUtil;

import java.util.LinkedList;
import java.util.List;

/**
 * @author With You
 * @version 1.0.0
 * @date 2020/2/25 22:13
 * @email 1713397546@qq.com
 * @description
 */
public class UrPermissionHelper {

    private Activity mActivity;
    private UrPermissionListener permissionListener;
    private static final int REQUEST_PERMISSIONS_CODE = 100;
    private LinkedList<String> mPermissionLists = null;

    private static volatile UrPermissionHelper instance;

    public static UrPermissionHelper getInstance() {
        if (instance == null) {
            synchronized (UrPermissionHelper.class) {
                if (instance == null) {
                    instance = new UrPermissionHelper();
                }
            }
        }
        return instance;
    }

    public UrPermissionHelper() {
    }

    public UrPermissionHelper with(Activity activity) {
        this.mActivity = activity;
        mPermissionLists = new LinkedList<>();
        return this;
    }

    public UrPermissionHelper addPermission(@NonNull String permission) {
        this.mPermissionLists.add(permission);
        return this;
    }

    public UrPermissionHelper addPermissionListener(@NonNull UrPermissionListener permissionInterface) {
        this.permissionListener = permissionInterface;
        return this;
    }

    public UrPermissionHelper(@NonNull Activity activity, @NonNull UrPermissionListener permissionInterface) {
        mActivity = activity;
        permissionListener = permissionInterface;
    }

    /**
     * 开始请求权限。
     * 方法内部已经对Android M 或以上版本进行了判断，外部使用不再需要重复判断。
     * 如果设备还不是M或以上版本，则也会回调到requestPermissionsSuccess方法。
     */
    public void requestPermissions() {
        if (isListEmpty(mPermissionLists))
            return;

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)//当版本小于6.0时
        {
            permissionListener.requestPermissionsSuccess();
            return;
        }
        String[] mPermissions = new String[mPermissionLists.size()];
        for (int i = 0; i < mPermissionLists.size(); i++) {
            mPermissions[i] = mPermissionLists.get(i);
        }
        String[] deniedPermissions = UrPermissionUtil.getDeniedPermissions(mActivity, mPermissions);
        if (deniedPermissions != null && deniedPermissions.length > 0) {
            UrPermissionUtil.requestPermissions(mActivity, deniedPermissions, REQUEST_PERMISSIONS_CODE);
        } else {
            permissionListener.requestPermissionsSuccess();
        }
    }

    /**
     * 在Activity中的onRequestPermissionsResult中调用
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     * @return true 代表对该requestCode感兴趣，并已经处理掉了。false 对该requestCode不感兴趣，不处理。
     */
    public boolean requestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_PERMISSIONS_CODE) {
            //是否全部权限已授权
            boolean isAllGranted = true;
            for (int result : grantResults) {
                if (result == PackageManager.PERMISSION_DENIED) {
                    isAllGranted = false;
                    break;
                }
            }
            if (isAllGranted) {
                //已全部授权
                permissionListener.requestPermissionsSuccess();
            } else {
                //权限有缺失
                permissionListener.requestPermissionsFail();
            }
            return true;
        }
        return false;
    }

    /**
     * 判断List是否为空
     */
    public static boolean isListEmpty(List<?> array) {
        return (array != null && array.size() == 0) || array == null;
    }
}
