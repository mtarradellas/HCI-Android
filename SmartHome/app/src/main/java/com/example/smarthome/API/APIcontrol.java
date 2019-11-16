package com.example.smarthome.API;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.example.smarthome.Device;
import com.example.smarthome.Room;
import com.example.smarthome.Routine;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class APIcontrol {
    private static final String apiUrl = "http://10.0.2.2:8080/api/";
    //private static final String apiUrl = "http://192.168.1.17:8080/api/";


    private static APIcontrol instance;
    private static RequestQueue requestQueue;

    private APIcontrol(Context context) {
        this.requestQueue = VolleySingleton.getInstance(context).getRequestQueue();
    }

    public static synchronized APIcontrol getInstance(Context context) {
        if (instance == null) {
            instance = new APIcontrol(context);
        }
        return instance;
    }

    public static String getApiUrl() {
        return apiUrl;
    }

    public String getRooms(Response.Listener<List<Room>> listener, Response.ErrorListener errorListener) {
        String url = apiUrl + "rooms/";
        GsonRequest<Object, List<Room>> request =
                new GsonRequest<Object, List<Room>>(Request.Method.GET, url, null, "rooms", new TypeToken<List<Room>>(){}, null, listener, errorListener);
        String uuid = UUID.randomUUID().toString();
        request.setTag(uuid);
        requestQueue.add(request);
        return uuid;
    }

    public String createRoom(Room room, Response.Listener<Room> listener, Response.ErrorListener errorListener) {
        String url = apiUrl + "rooms";
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        GsonRequest<Room, Room> request =
                new GsonRequest<Room, Room>(Request.Method.POST, url, room, "room", new TypeToken<Room>() {
                }, headers, listener, errorListener);
        String uuid = UUID.randomUUID().toString();
        request.setTag(uuid);
        requestQueue.add(request);

        return uuid;
    }
}
