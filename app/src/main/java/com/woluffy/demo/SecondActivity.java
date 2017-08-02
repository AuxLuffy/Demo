package com.woluffy.demo;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

import static android.Manifest.permission.CAMERA;

public class SecondActivity extends AppCompatActivity {

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        checkPermission();
//        boolean is = cameraPermission();
        boolean is = EasyPermissions.hasPermissions(this, Manifest.permission.CAMERA);
        Log.e("tag", "checkPermission: " + is);
        if(!shouldShowRequestPermissionRationale(CAMERA)) {
            Log.e("tag2", "onCreate: " + "camera permission has denied!" );
        }
    }

    private void checkPermission() {
        final List<String> permissionsList = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if ((checkSelfPermission(CAMERA) != PackageManager.PERMISSION_GRANTED))
                permissionsList.add(CAMERA);
            if (permissionsList.size() != 0) {
                requestPermissions(permissionsList.toArray(new String[permissionsList.size()]),
                        0);
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 0) {
            Log.e("tag2", "cameraPermission: " + cameraPermission());
        }
        for (int i = 0; i < grantResults.length; i++) {
            if(grantResults[i] == PackageManager.PERMISSION_DENIED) {
                Log.e("tag3", "onRequestPermissionsResult: "+ permissions[i] + "is denied!" );
            }
        }
        EasyPermissions.onRequestPermissionsResult(requestCode,permissions,grantResults,this);
    }

    private boolean cameraPermission() {
       /* try {
            Camera.open().release();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }*/

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        if(permissionCheck == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;
    }
}
