package com.example.smarthome;

import java.io.Serializable;
import java.util.List;

public class Action implements Serializable {
    private DeviceId device; //must only have id
    private String actionName;
    private List<String> params;//if action has no params it still has to be initialized as an empty list
    private String meta;

    public Action(DeviceId device, String name, List<String> params, String meta){
        this(device, name, params);
        this.meta = meta;
    }

    public Action(DeviceId device, String name, List<String> params){
        this.device = device;
        this.actionName = name;
        this.params = params;
    }

    @Override
    public String toString() {
        return "Action{" +
                "device='" + device.getId() + '\'' +
                ", actionName='" + actionName + '}';
    }

    public DeviceId getDeviceId() {
        return device;
    }

    public List<String> getParams() {
        return params;
    }

    public String getActionName() {
        return actionName;
    }
}
