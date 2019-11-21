package com.example.smarthome;

import java.io.Serializable;

public class Device implements Serializable {
    private String id;
    private String name;
    private Type type ;
    private Meta meta; //will be empty




    public Device(String name, Type typeId, Meta meta){
        this.name = name;
        this.type = typeId;
        this.meta = meta;
    }

    public Device(String id, String name, Type typeId, Meta meta){
        this.name = name;
        this.type = typeId;
        this.meta = meta;
        this.id = id;

    }


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

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public Meta getMeta() {
        return this.meta;
    }

    @Override
    public String toString() {
        return "Id: " + this.id + "; typeId: "+ this.type.getTypeId() + "; name: "+ this.name+"; meta: "+ this.meta.toString();
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
