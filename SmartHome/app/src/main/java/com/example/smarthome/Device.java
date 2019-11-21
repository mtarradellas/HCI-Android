package com.example.smarthome;

import java.io.Serializable;

public class Device implements Serializable {
    private String id;
    private String name;
    private Type type ;
    private String meta; //will be empty




    public Device(String name, Type typeId, String meta){
        this.name = name;
        this.type = typeId;
        this.meta = meta;
    }

    public Device(String id, String name, Type typeId, String meta){
        this.name = name;
        this.type = typeId;
        this.meta = meta;
        this.id = id;

    }


//    public void onClickAction(Serializable arg, final Context context) {
//
//    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Type getTypeId() { return type; }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public String getMeta() {
        return this.meta;
    }

    @Override
    public String toString() {
        return "Id: " + this.id + "; typeId: "+ this.type.getTypeId() + "; name: "+ this.name+"; meta: "+ this.meta;
    }

    @Override
    public boolean equals(Object o){
        if(o == null || !(o instanceof Device)) {
            return false;
        }

        Device d = (Device)o;
        return this.name.equals(d.name) && this.type.equals(d.type) && this.meta.equals(d.meta);
    }

}
