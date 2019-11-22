package com.example.smarthome.ui.Favourites;

import com.example.smarthome.Device;

public class FavouritesItem {
    private Device favourite;
    private int img;

    public FavouritesItem(Device favourite, int img) {
        this.favourite = favourite;
        this.img = img;
    }

    public Device getFavourite() {
        return favourite;
    }

    public void setFavourite(Device favourite) {
        this.favourite = favourite;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
