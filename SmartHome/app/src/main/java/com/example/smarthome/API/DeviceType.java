package com.example.smarthome.API;

import com.example.smarthome.R;
import com.example.smarthome.TypeId;

import java.util.EnumSet;

public class DeviceType {

    public static int getImg(String type) {
        Integer[] toReturn = {R.drawable.no_image};
        EnumSet.allOf(TypeId.class).forEach(t -> {
            if (t.getTypeId().equals(type)) {
                toReturn[0] = t.getImg();
            }
        });
        return toReturn[0];
    }
}
