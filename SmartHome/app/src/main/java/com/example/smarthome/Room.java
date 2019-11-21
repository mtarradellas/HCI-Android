package com.example.smarthome;

public class Room {
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
}