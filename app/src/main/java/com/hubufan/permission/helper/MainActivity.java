package com.hubufan.permission.helper;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Bundle;

import com.hubufan.permission.UrPermissionHelper;
import com.hubufan.permission.listener.UrPermissionListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UrPermissionHelper.getInstance().with(this)
                .addPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .addPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .addPermissionListener(new UrPermissionListener() {

                    @Override
                    public void requestPermissionsSuccess() {
                    }

                    @Override
                    public void requestPermissionsFail() {
                    }
                }).requestPermissions();
    }
}
