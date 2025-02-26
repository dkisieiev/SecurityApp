package com.example.securityapp;

import android.hardware.Camera;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;

public class MultiCameraActivity extends AppCompatActivity {

    private SurfaceView cameraView1, cameraView2;
    private Camera camera1, camera2;
    private SurfaceHolder holder1, holder2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_camera);

        cameraView1 = findViewById(R.id.cameraView1);
        cameraView2 = findViewById(R.id.cameraView2);

        holder1 = cameraView1.getHolder();
        holder2 = cameraView2.getHolder();

        holder1.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                openCamera(0, holder);
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                if (camera1 != null) {
                    camera1.stopPreview();
                    camera1.release();
                    camera1 = null;
                }
            }
        });

        holder2.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                openCamera(1, holder);
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                if (camera2 != null) {
                    camera2.stopPreview();
                    camera2.release();
                    camera2 = null;
                }
            }
        });
    }

    private void openCamera(int cameraId, SurfaceHolder holder) {
        try {
            Camera camera = Camera.open(cameraId);
            camera.setPreviewDisplay(holder);
            camera.startPreview();
            if (cameraId == 0) {
                camera1 = camera;
            } else {
                camera2 = camera;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
