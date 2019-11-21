package com.example.smarthome;

import java.io.Serializable;
import java.util.Objects;

public class Meta implements Serializable {
    private String string;


    public Meta(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return string;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meta meta1 = (Meta) o;
        return Objects.equals(string, meta1.string);
    }

    @Override
    public int hashCode() {
        return Objects.hash(string);
    }
}
