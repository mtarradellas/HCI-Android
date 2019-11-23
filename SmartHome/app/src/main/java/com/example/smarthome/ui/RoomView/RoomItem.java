package com.example.smarthome.ui.RoomView;

import com.example.smarthome.API.DeviceType;
import com.example.smarthome.Device;
import com.example.smarthome.R;

public class RoomItem {
    private Device device;
    private int img;

    public RoomItem(Device device, int img) {
        this.device = device;
        this.img = img;
    }

    public RoomItem(Device device) {
        this.device = device;
        this.img = DeviceType.getImg(device.getTypeId().toString());
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
