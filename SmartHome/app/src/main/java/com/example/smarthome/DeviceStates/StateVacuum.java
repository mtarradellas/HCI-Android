package com.example.smarthome.DeviceStates;

import java.io.Serializable;

public class StateVacuum implements Serializable {
    private String status;
    //pausado es parte del estado?
    private String mode;
    private String ubicationId;

    public StateVacuum(String status, String ubicationId, String mode) {
        this.mode = mode;
        this.status = status;
        this.ubicationId = ubicationId;
    }

    public StateVacuum() {

    }

    public String getStatus() {
        return status;
    }

    public String getMode() {
        return mode;
    }

    public String getUbicationId() {
        return ubicationId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public void setUbicationId(String ubicationId) {
        this.ubicationId = ubicationId;
    }
}
