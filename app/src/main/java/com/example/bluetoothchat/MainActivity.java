package com.example.bluetoothchat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public void signUp(View view) {
        Log.i("Info", "SignUp Button pressed\n");

        Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

   public void login(View view) {
        Log.i("Info", "Login Button pressed\n");

        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.signUpButton);

    }
}