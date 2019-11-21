package com.example.smarthome.ui;

import java.io.Serializable;
import java.util.Objects;

public class Type implements Serializable {

    private String id;

    public Type(String typeId) {
        this.id = typeId;
    }

    public String getTypeId() {
        return id;
    }

    public void setTypeId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Type type = (Type) o;
        return Objects.equals(id, type.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
