package com.example.smarthome;



import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Routine implements Serializable, Parcelable {
    private String id;
    private String name;
    private ArrayList<Action> actions;
    private Meta meta;

    public Routine(String id, String name, ArrayList<Action> actions, Meta meta){
        this(name, actions, meta);
        this.id = id;

    }

    public Routine(String name, ArrayList<Action> actions, Meta meta){
        this.meta = meta;
        this.actions = actions;
        this.name = name;
    }

    protected Routine(Parcel in) {
        id = in.readString();
        name = in.readString();
        actions = in.createTypedArrayList(Action.CREATOR);
        meta = in.readParcelable(Meta.class.getClassLoader());
    }

    public static final Creator<Routine> CREATOR = new Creator<Routine>() {
        @Override
        public Routine createFromParcel(Parcel in) {
            return new Routine(in);
        }

        @Override
        public Routine[] newArray(int size) {
            return new Routine[size];
        }
    };

    public boolean containsDevice(Device device){
        for(Action action: actions){
            if(action.getDeviceId().getId().equals(device.getId()))
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Routine{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setActions(ArrayList<Action> actions) {
        this.actions = actions;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<Action> getActions() {
        return actions;
    }
    public String getName(){return name;}
    public Meta getMeta(){return meta;}
    public String getId(){return id;}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeTypedList(actions);
        dest.writeParcelable(meta, flags);
    }
}
