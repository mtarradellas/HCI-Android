package com.example.smarthome.DeviceStates;

import java.io.Serializable;

public class StateStereo implements Serializable {
    private int volume;
    private String status;

    public StateStereo(int volume, String status) {
        this.status = status;
        this.volume = volume;
    }

    public StateStereo() {

    }

    public int getVolume() {
        return volume;
    }

    public String getStatus() {
        return status;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
