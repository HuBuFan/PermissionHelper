# UrPermissionHelper
6.0+权限相关使用：

``` 
dependencies {
        implementation 'com.github.HuBuFan:UrPermissionHelper:v1.1.0'
}

allprojects {
    repositories {
        ...
        maven { url 'https://www.jitpack.io' }
    }
}
``` 
使用方式：

``` 
UrPermissionHelper.getInstance().with(this)
        .addPermission(Manifest.permission.CAMERA)
        .addPermission(Manifest.permission.READ_PHONE_STATE)
        .addPermission(Manifest.permission.CALL_PHONE)
        ...
        .addPermissionListener(new UrPermissionListener() {

            @Override
            public void requestPermissionsSuccess() {
               Toast.makeText(mContext, "授权成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void requestPermissionsFail() {
               Toast.makeText(mContext, "授权失败", Toast.LENGTH_SHORT).show();
            }
        }).requestPermissions();
``` 
