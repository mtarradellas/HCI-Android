package com.example.smarthome;

import java.io.Serializable;
import java.util.Objects;

public class DeviceId implements Serializable {
    private String id;

    public DeviceId(String id) {
        this.id = id;
    }

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
