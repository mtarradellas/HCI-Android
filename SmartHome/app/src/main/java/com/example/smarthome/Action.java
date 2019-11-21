package com.example.smarthome;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Action implements Serializable {
    private DeviceId device; //must only have id
    private String actionName;
    private List<String> params;//if action has no params it still has to be initialized as an empty list
    private Meta meta;

    public Action(DeviceId device, String name, List<String> params, Meta meta){
        this(device, name);
        this.params = params;
        this.meta = meta;
    }

    public Action(DeviceId device, String name){
        this.device = device;
        this.actionName = name;
        this.params = new ArrayList<>();
        this.meta = new Meta(null);
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
