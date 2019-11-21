package com.example.smarthome.DeviceStates;

import java.io.Serializable;

public class StateDoor implements Serializable {
    private String status;
    private String lock;

    public StateDoor(String status, String lock){
        this.status = status;
        this.lock = lock;
    }

    public StateDoor(){

    }

    @Override
    public String toString() {
        return "StateDoor{" +
                "status='" + status + '\'' +
                ", lock='" + lock + '\'' +
                '}';
    }

    public String getStatus() {
        return status;
    }

    public String getLock() {
        return lock;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setLock(String lock) {
        this.lock = lock;
    }
}
