package com.example.smarthome.ui;

public class StateBlinds {
    private String status;
    private int level;

    public StateBlinds(String status, int level){
        this.status = status;
        this.level = level;
    }

    public StateBlinds(){

    }

    public String getStatus() {
        return status;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
