package com.example.smarthome;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Device implements Serializable, Parcelable {
    private String id;
    private String name;
    private Type type ;
    private DeviceMeta meta; //has boolean favourite




    public Device(String name, Type typeId, DeviceMeta meta){
        this.name = name;
        this.type = typeId;
        this.meta = meta;
    }

    public Device(String id, String name, Type typeId, DeviceMeta meta){
        this.name = name;
        this.type = typeId;
        this.meta = meta;
        this.id = id;

    }

    protected Device(Parcel in) {
        id = in.readString();
        name = in.readString();
        type = in.readParcelable(Type.class.getClassLoader());
        meta = in.readParcelable(DeviceMeta.class.getClassLoader());
    }

    public static final Creator<Device> CREATOR = new Creator<Device>() {
        @Override
        public Device createFromParcel(Parcel in) {
            return new Device(in);
        }

        @Override
        public Device[] newArray(int size) {
            return new Device[size];
        }
    };

    public Boolean isFav() {
        if(meta == null){
            return false;
        }
        return meta.getFavourite();
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

    public void setMeta(DeviceMeta meta) {
        this.meta = meta;
    }

    public DeviceMeta getMeta() {
        return this.meta;
    }

    @Override
    public String toString() {
        if(meta == null){
            return "Id: " + this.id + "; typeId: "+ this.type.getTypeId() + "; name: "+ this.name;
        }
        return "Id: " + this.id + "; typeId: "+ this.type.getTypeId() + "; name: "+ this.name+ "favourite: "+ meta.getFavourite().toString();
    }

    @Override
    public boolean equals(Object o){
        if(o == null || !(o instanceof Device)) {
            return false;
        }

        Device d = (Device)o;
        return this.name.equals(d.name) && this.type.equals(d.type) && this.meta.equals(d.meta);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeParcelable(type, flags);
        dest.writeParcelable(meta, flags);
    }
}
