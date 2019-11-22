package com.example.smarthome.ui.RoomView;

import com.example.smarthome.Device;

public class RoomItem {
    private Device device;
    private int img;

    public RoomItem(Device device, int img) {
        this.device = device;
        this.img = img;
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
