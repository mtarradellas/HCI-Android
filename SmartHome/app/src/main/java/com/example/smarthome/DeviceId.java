package com.example.smarthome;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Objects;

public class DeviceId implements Serializable, Parcelable {
    private String id;

    public DeviceId(String id) {
        this.id = id;
    }

    protected DeviceId(Parcel in) {
        id = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DeviceId> CREATOR = new Creator<DeviceId>() {
        @Override
        public DeviceId createFromParcel(Parcel in) {
            return new DeviceId(in);
        }

        @Override
        public DeviceId[] newArray(int size) {
            return new DeviceId[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "DeviceId{" +
                "id='" + id + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeviceId deviceId = (DeviceId) o;
        return Objects.equals(id, deviceId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
