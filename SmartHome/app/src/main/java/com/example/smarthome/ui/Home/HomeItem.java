package com.example.smarthome.ui.Home;

import android.widget.ImageView;

import com.example.smarthome.Room;

public class HomeItem {
    private Room room;
    private int img;

    public HomeItem(Room room, int img) {
        this.room = room;
        this.img = img;
    }

    public Room getRoom() {
        return room;
    }

    public int getImg() {
        return img;
    }
}
