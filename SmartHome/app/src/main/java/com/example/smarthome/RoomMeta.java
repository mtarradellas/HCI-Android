package com.example.smarthome;

public class RoomMeta {
    private String size;

    public RoomMeta(String size) {
        this.size = size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSize() {
        return this.size;
    }

    @Override
    public String toString() {
        return this.getSize();
    }
}
