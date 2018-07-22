package com.example.miljanrakita.instagramlibrary;

import android.Manifest;
import android.content.pm.PackageManager;
import android.provider.SyncStateContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;


import lombok.NonNull;

public class MainActivity extends AppCompatActivity {

    int REQUEST_CODE_INTERNET_PERMISSIONS = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestPermission(Manifest.permission.INTERNET);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {

            case 2:
                Toast.makeText(getApplicationContext(), "all good", Toast.LENGTH_LONG).show();
                break;
        }
    }

    private void requestPermission(String permission) {
        if (ContextCompat.checkSelfPermission(getApplicationContext(), permission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{permission}, REQUEST_CODE_INTERNET_PERMISSIONS);
        }


        Toast.makeText(getApplicationContext(), "already got it", Toast.LENGTH_LONG).show();
    }

}
