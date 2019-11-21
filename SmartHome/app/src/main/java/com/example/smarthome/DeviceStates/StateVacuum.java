package com.example.smarthome.DeviceStates;

import java.io.Serializable;

public class StateVacuum implements Serializable {
    private String status;
    private String mode;
    private int batteryLevel;

    public StateVacuum(String status, int batteryLevel, String mode) {
        this.mode = mode;
        this.status = status;
        this.batteryLevel = batteryLevel;
    }

    public StateVacuum() {

    }

    @Override
    public String toString() {
        return "StateVacuum{" +
                "status='" + status + '\'' +
                ", mode='" + mode + '\'' +
                ", batteryLevel=" + batteryLevel +
                '}';
    }

    public String getStatus() {
        return status;
    }

    public String getMode() {
        return mode;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(int batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

}
