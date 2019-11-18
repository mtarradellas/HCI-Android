package com.example.smarthome;

import java.io.Serializable;
import java.util.List;

public class Action implements Serializable {
    private String deviceId;
    private String actionName;
    private List<String> params;
    private String meta;

    public Action(String deviceId, String name, List<String> params, String meta){
        this(deviceId, name, params);
        this.meta = meta;
    }

    public Action(String deviceId, String name, List<String> params){
        this.deviceId = deviceId;
        this.actionName = name;
        this.params = params;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public List<String> getParams() {
        return params;
    }

    public String getActionName() {
        return actionName;
    }
}
