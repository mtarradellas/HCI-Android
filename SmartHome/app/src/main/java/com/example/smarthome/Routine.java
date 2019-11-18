package com.example.smarthome;



import java.io.Serializable;
import java.util.List;

public class Routine implements Serializable{
    private String id;
    private String name;
    private List<Action> actions;
    private String meta;

    public Routine(String id, String name, List<Action> actions, String meta){
        this(name, actions, meta);
        this.id = id;

    }

    public Routine(String name, List<Action> actions, String meta){
        this.meta = meta;
        this.actions = actions;
        this.name = name;
    }

    public boolean containsDevice(Device device){
        for(Action action: actions){
            if(action.getDeviceId().equals(device.getId()))
                return true;
        }
        return false;
    }

    public List<Action> getActions() {
        return actions;
    }
    public String getName(){return name;}
    public String getMeta(){return meta;}
    public String getId(){return id;}

}
