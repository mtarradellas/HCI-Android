package com.example.smarthome.ui.Routines;

import com.example.smarthome.Routine;

public class RoutinesItem {

    private Routine routine;
    private int img;

    public RoutinesItem(Routine routine, int img) {
        this.routine = routine;
        this.img = img;
    }

    public void setRoutine(Routine routine) {
        this.routine = routine;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public Routine getRoutine() {
        return routine;
    }

    public int getImg() {
        return img;
    }
}
