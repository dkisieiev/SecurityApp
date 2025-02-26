package com.example.securityapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openCamera(View view) {
        startActivity(new Intent(this, CameraActivity.class));
    }

    public void openRemoteAccess(View view) {
        startActivity(new Intent(this, RemoteAccessActivity.class));
    }

    public void openMultiCamera(View view) {
        startActivity(new Intent(this, MultiCameraActivity.class));
    }
}
