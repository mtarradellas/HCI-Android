package com.example.smarthome.ui.Home;

import com.example.smarthome.Room;

public class HomeItem {
    private Room room;

    public HomeItem(String name, String meta) {
        room = new Room(name, meta);
    }

}
