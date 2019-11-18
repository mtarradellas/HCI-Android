package com.example.smarthome.ui;

import java.io.Serializable;

public class StateAlarm implements Serializable {
    private String status;

    public StateAlarm(String status){
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
