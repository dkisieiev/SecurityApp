package com.example.securityapp;

import android.app.Service;
import android.content.Intent;
import android.hardware.Camera;
import android.os.IBinder;
import android.util.Log;
import androidx.annotation.Nullable;

public class MotionDetectionService extends Service implements Camera.PreviewCallback {
    private Camera camera;

    @Override
    public void onCreate() {
        super.onCreate();
        startMotionDetection();
    }

    private void startMotionDetection() {
        try {
            camera = Camera.open();
            Camera.Parameters parameters = camera.getParameters();
            camera.setPreviewCallback(this);
            camera.startPreview();
        } catch (Exception e) {
            Log.e("MotionDetection", "Ошибка открытия камеры", e);
        }
    }

    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {
        // Простая проверка на изменение пикселей (нужно улучшить алгоритм)
        if (data != null && data.length > 0) {
            Log.d("MotionDetection", "Движение обнаружено!");
            sendAlert();
        }
    }

    private void sendAlert() {
        // Можно добавить push-уведомления или e-mail
        Log.d("MotionDetection", "Отправка уведомления о движении");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (camera != null) {
            camera.setPreviewCallback(null);
            camera.release();
            camera = null;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
