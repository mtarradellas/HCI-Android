package com.example.smarthome;

import android.os.Parcel;
import android.os.Parcelable;

public class Room implements Parcelable {
    private String id;
    private String name;
    private Meta meta;

    public Room(String name, Meta meta) {
        this.name = name;
        this.meta = meta;
    }

    public Room(String id, String name, Meta meta) {
        this.id = id;
        this.name = name;
        this.meta = meta;
    }

    public Room( String name) {
        this.name = name;
        this.meta = new Meta(null);
    }

    protected Room(Parcel in) {
        id = in.readString();
        name = in.readString();
    }

    public static final Creator<Room> CREATOR = new Creator<Room>() {
        @Override
        public Room createFromParcel(Parcel in) {
            return new Room(in);
        }

        @Override
        public Room[] newArray(int size) {
            return new Room[size];
        }
    };

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public Meta getMeta() {
        return this.meta;
    }

    @Override
    public String toString() {
        if (this.getId() != null)
        {
            if (this.getMeta().getString() != null)
                return String.format("%s - %s - %s", this.getId(), this.getName(), this.getMeta().toString());
            else
                return String.format("%s - %s", this.getId(), this.getName());
        }
        else
        {
            if (this.getMeta() != null)
                return String.format("%s - %s", this.getName(), this.getMeta().toString());
            else
                return this.getName();
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
    }
}