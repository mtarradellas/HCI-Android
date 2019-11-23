package com.example.smarthome;

import java.io.Serializable;
import java.util.Objects;

public class DeviceMeta implements Serializable {
    private Boolean favourite;

    public DeviceMeta(Boolean favourite) {

        this.favourite = (favourite == null) ? false : favourite;
    }
    public DeviceMeta() {
        this.favourite = false;
    }

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
}
