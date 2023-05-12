package com.example.bluetoothchat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    public void bluetoothChat(View view) {
        Log.i("Info", "Login Button is pressed\n");

        Intent intent = new Intent(LoginActivity.this, BluetoothChatActivity.class);
        startActivity(intent);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}