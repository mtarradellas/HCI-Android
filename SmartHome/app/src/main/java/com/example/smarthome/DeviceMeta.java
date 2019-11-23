package com.example.smarthome;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Objects;

public class DeviceMeta implements Serializable, Parcelable {
    private Boolean favourite;

    public DeviceMeta(Boolean favourite) {

        this.favourite = (favourite == null) ? false : favourite;
    }
    public DeviceMeta() {
        this.favourite = false;
    }

    protected DeviceMeta(Parcel in) {
        byte tmpFavourite = in.readByte();
        favourite = tmpFavourite == 0 ? null : tmpFavourite == 1;
    }

    public static final Creator<DeviceMeta> CREATOR = new Creator<DeviceMeta>() {
        @Override
        public DeviceMeta createFromParcel(Parcel in) {
            return new DeviceMeta(in);
        }

        @Override
        public DeviceMeta[] newArray(int size) {
            return new DeviceMeta[size];
        }
    };

    public Boolean getFavourite() {
        return favourite;
    }

    public void setFavourite(Boolean favourite) {
        this.favourite = favourite;
    }

    @Override
    public String toString() {
        return "DeviceMeta{" +
                "favourite=" + favourite +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeviceMeta that = (DeviceMeta) o;
        return Objects.equals(favourite, that.favourite);
    }

    @Override
    public int hashCode() {
        return Objects.hash(favourite);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (favourite == null ? 0 : favourite ? 1 : 2));
    }
}
