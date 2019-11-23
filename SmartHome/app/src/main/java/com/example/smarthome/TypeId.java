package com.example.smarthome;

public enum TypeId {
    AC("li6cbv5sdlatti0j", R.drawable.ac),
    BLIND("eu0v2xgprrhhg41g", R.drawable.blind),
    DOOR("lsf78ly0eqrjbz91", R.drawable.door),
    LAMP("go46xmbqeomjrsjr", R.drawable.lamp),
    SPEAKER("c89b94e8581855bc", R.drawable.speaker),
    VACUUM("ofglvd9gqX8yfl3l", R.drawable.vacuum),
    ALARM("mxztsyjzsrq7iaqc", R.drawable.alarm);

    private String typeId;
    private int img;

    TypeId(String typeId, int img){
        this.typeId = typeId;
        this.img = img;
    }

    public String getTypeId() {
        return typeId;
    }

    public int getImg() {
        return img;
    }
}