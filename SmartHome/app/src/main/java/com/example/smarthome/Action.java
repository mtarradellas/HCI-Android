package com.example.smarthome;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Action implements Serializable, Parcelable {
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
        String string = null;
        this.meta = new Meta(string);
    }

    protected Action(Parcel in) {
        device = in.readParcelable(DeviceId.class.getClassLoader());
        actionName = in.readString();
        params = in.createStringArrayList();
        meta = in.readParcelable(Meta.class.getClassLoader());
    }

    public static final Creator<Action> CREATOR = new Creator<Action>() {
        @Override
        public Action createFromParcel(Parcel in) {
            return new Action(in);
        }

        @Override
        public Action[] newArray(int size) {
            return new Action[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(device, flags);
        dest.writeString(actionName);
        dest.writeStringList(params);
        dest.writeParcelable(meta, flags);
    }
}
