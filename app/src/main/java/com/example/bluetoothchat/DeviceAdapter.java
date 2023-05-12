package com.example.bluetoothchat;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DeviceAdapter extends RecyclerView.Adapter<DeviceAdapter.DeviceViewHolder> {

    private List<BluetoothDevice> deviceList;
    private Context context;

    private static final int REQUEST_BLUETOOTH_PERMISSION = 1;

    public DeviceAdapter(List<BluetoothDevice> deviceList) {
        this.deviceList = deviceList;
        this.context = context;
    }

    @NonNull
    @Override
    public DeviceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_device, parent, false);
        return new DeviceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeviceViewHolder holder, int position) {
        BluetoothDevice device = deviceList.get(position);
        holder.bind(device);
    }

    @Override
    public int getItemCount() {
        return deviceList.size();
    }

    public class DeviceViewHolder extends RecyclerView.ViewHolder {

        private TextView deviceNameTextView;

        public DeviceViewHolder(@NonNull View itemView) {
            super(itemView);
            deviceNameTextView = itemView.findViewById(R.id.deviceNameTextView);
        }

        public void bind(BluetoothDevice device) {
            // Check for permission before accessing the device name
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH_CONNECT)
                    != PackageManager.PERMISSION_GRANTED) {
                // Permission is not granted, request it
                ActivityCompat.requestPermissions((Activity) context,
                        new String[]{Manifest.permission.BLUETOOTH_CONNECT},
                        REQUEST_BLUETOOTH_PERMISSION);
            } else {
                // Permission is granted, access the device name
                String deviceName = device.getName();
                if (deviceName != null) {
                    deviceNameTextView.setText(deviceName);
                } else {
                    deviceNameTextView.setText(R.string.unknown_device);
                }
            }
        }
    }
}
