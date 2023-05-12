package com.example.bluetoothchat;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class BluetoothChatActivity extends AppCompatActivity {

    private RecyclerView chatRecyclerView;
    private MessageAdapter messageAdapter;

    private BluetoothAdapter bluetoothAdapter;

    private ActivityResultLauncher<Intent> enableBluetoothLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth_chat);

        // Initialize RecyclerView
        chatRecyclerView = findViewById(R.id.chatRecyclerView);
        chatRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        messageAdapter = new MessageAdapter();
        chatRecyclerView.setAdapter(messageAdapter);

        // Initialize BluetoothAdapter
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            Toast.makeText(this, "Bluetooth is not supported on this device", Toast.LENGTH_SHORT).show();
        }

        // Create the launcher for enabling Bluetooth
        enableBluetoothLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        // Bluetooth was successfully enabled
                        Toast.makeText(this, "Bluetooth is enabled", Toast.LENGTH_SHORT).show();
                    } else {
                        // Bluetooth enabling was canceled or failed
                        Toast.makeText(this, "Failed to enable Bluetooth", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_search_device) {
            // Handle "Search Device" menu item click
            searchDevice();
            return true;
        } else if (id == R.id.action_switch_bluetooth) {
            // Handle "Switch ON Bluetooth" menu item click
            switchBluetooth();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void searchDevice() {
        // Perform the search device functionality
        Intent searchIntent = new Intent(BluetoothChatActivity.this, SearchDeviceActivity.class);
        startActivity(searchIntent);
    }

    private void switchBluetooth() {
        // Perform the switch on Bluetooth functionality
        if (bluetoothAdapter != null) {
            if (!bluetoothAdapter.isEnabled()) {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                enableBluetoothLauncher.launch(enableBtIntent);
            } else {
                Toast.makeText(this, "Bluetooth is already enabled", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
