package com.example.smarthome;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Action implements Serializable, Parcelable {
    private DeviceId device; //must only have id
    private String actionName;
    private List<String> paramsString;
    private List<Integer> paramsInteger;//if action has no params it still has to be initialized as an empty list
    private Meta meta;

    public Action(DeviceId device, String name, List<String> paramsString, List<Integer> paramsInteger, Meta meta){
        this(device, name);
        this.paramsString = paramsString;
        this.paramsInteger = paramsInteger;
        this.meta = meta;
    }

    public Action(DeviceId device, String name){
        this.device = device;
        this.actionName = name;
        this.paramsString = new ArrayList<>();
        this.paramsInteger = new ArrayList<>();
        String string = null;
        this.meta = new Meta(string);
    }

    protected Action(Parcel in) {
        device = in.readParcelable(DeviceId.class.getClassLoader());
        actionName = in.readString();
        paramsString = in.createStringArrayList();
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

    public List<String> getParamsString() {
        return paramsString;
    }

    public List<Integer> getParamsInteger() {
        return paramsInteger;
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
        dest.writeStringList(paramsString);
        dest.writeParcelable(meta, flags);
    }
}
