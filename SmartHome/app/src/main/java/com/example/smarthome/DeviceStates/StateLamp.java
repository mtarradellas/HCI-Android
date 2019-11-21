package com.example.smarthome.DeviceStates;

import java.io.Serializable;

public class StateLamp implements Serializable {
    private String status;
    private String color;
    private int brightness;

    public StateLamp(String status, String color, int brightness) {
        this.status = status;
        this.color = color;
        this.brightness = brightness;
    }

    public StateLamp() {  ///la necesito?

    }

    @Override
    public String toString() {
        return "StateLamp{" +
                "status='" + status + '\'' +
                ", color='" + color + '\'' +
                ", brightness=" + brightness +
                '}';
    }

    public int getBrightness() {
        return brightness;
    }

    public String getColor() {
        return color;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setBrightness(int brightness) {
        this.brightness = brightness;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
