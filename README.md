超级nice的6.0+权限请求

``` 
dependencies {
        implementation 'com.github.HuBuFan:UrPermissionHelper:latestVersion'
}

allprojects {
    repositories {
        ...
        maven { url 'https://www.jitpack.io' }
    }
}
``` 
- 使用方式：

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
