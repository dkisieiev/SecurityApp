package com.example.securityapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class RemoteAccessActivity extends AppCompatActivity {

    private EditText etIpAddress;
    private Button btnConnect;
    private TextView tvStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_access);

        etIpAddress = findViewById(R.id.etIpAddress);
        btnConnect = findViewById(R.id.btnConnect);
        tvStatus = findViewById(R.id.tvStatus);

        btnConnect.setOnClickListener(view -> connectToServer(etIpAddress.getText().toString()));
    }

    private void connectToServer(String ipAddress) {
        new Thread(() -> {
            try {
                URL url = new URL("http://" + ipAddress + ":5000/connect");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String response = reader.readLine();
                reader.close();

                runOnUiThread(() -> tvStatus.setText("Статус: " + response));

            } catch (Exception e) {
                runOnUiThread(() -> tvStatus.setText("Ошибка подключения"));
                e.printStackTrace();
            }
        }).start();
    }
}
