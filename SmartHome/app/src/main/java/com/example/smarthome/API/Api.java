package com.example.smarthome.API;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.example.smarthome.Action;
import com.example.smarthome.Device;
import com.example.smarthome.Room;
import com.example.smarthome.Routine;
import com.example.smarthome.ui.StateAC;
import com.example.smarthome.ui.StateAlarm;
import com.example.smarthome.ui.StateBlinds;
import com.example.smarthome.ui.StateDoor;
import com.example.smarthome.ui.StateLamp;
import com.example.smarthome.ui.StateStereo;
import com.example.smarthome.ui.StateVacuum;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Api {
    private static Api instance;
    private static RequestQueue requestQueue;
    // Use IP 10.0.2.2 instead of 127.0.0.1 when running Android emulator in the
    // same computer that runs the API.
    private final String URL = "http://10.0.2.2:8080/api/";

    private Api(Context context) {
        this.requestQueue = VolleySingleton.getInstance(context).getRequestQueue();
    }

    public static synchronized Api getInstance(Context context) {
        if (instance == null) {
            instance = new Api(context);
        }
        return instance;
    }

    ///////////     ROOMS       ///////////////////////

    public String addRoom(Room room, Response.Listener<Room> listener, Response.ErrorListener errorListener) {
        String url = URL + "rooms";
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        GsonRequest<Room, Room> request =
                new GsonRequest<>(Request.Method.POST, url, room, "result", new TypeToken<Room>(){}, headers, listener, errorListener);
        String uuid = UUID.randomUUID().toString();
        request.setTag(uuid);
        requestQueue.add(request);

        return uuid;
    }

    public String modifyRoom(Room room, Response.Listener<Boolean> listener, Response.ErrorListener errorListener) {
        String url = URL + "rooms/" + room.getId();
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        GsonRequest<Room, Boolean> request =
                new GsonRequest<>(Request.Method.PUT, url, room, "result", new TypeToken<Boolean>(){}, headers, listener, errorListener);
        String uuid = UUID.randomUUID().toString();
        request.setTag(uuid);
        requestQueue.add(request);

        return uuid;
    }

    public String deleteRoom(String id, Response.Listener<Boolean> listener, Response.ErrorListener errorListener) {
        String url = URL + "rooms/" + id;
        GsonRequest<Object, Boolean> request =
                new GsonRequest<>(Request.Method.DELETE, url, null, "result", new TypeToken<Boolean>(){}, null, listener, errorListener);
        String uuid = UUID.randomUUID().toString();
        request.setTag(uuid);
        requestQueue.add(request);

        return uuid;
    }

    public String getRoom(String id, Response.Listener<Room> listener, Response.ErrorListener errorListener) {
        String url = URL + "rooms/" + id;
        GsonRequest<Object, Room> request =
                new GsonRequest<>(Request.Method.GET, url, null, "result", new TypeToken<Room>(){}, null, listener, errorListener);
        String uuid = UUID.randomUUID().toString();
        request.setTag(uuid);
        requestQueue.add(request);

        return uuid;
    }

    public String getRooms(Response.Listener<ArrayList<Room>> listener, Response.ErrorListener errorListener) {
        String url = URL + "rooms/";
        GsonRequest<Object, ArrayList<Room>> request =
                new GsonRequest<>(Request.Method.GET, url, null, "result", new TypeToken<ArrayList<Room>>(){}, null, listener, errorListener);
        String uuid = UUID.randomUUID().toString();
        request.setTag(uuid);
        requestQueue.add(request);
        return uuid;
    }


    ///////////////     DEVICES     ////////////////

    public String createDevice(Device device, Response.Listener<Device> listener, Response.ErrorListener errorListener) {
        String url = URL + "devices/";
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        GsonRequest<Device, Device> request =
                new GsonRequest<>(Request.Method.POST, url, device, "result", new TypeToken<Device>(){}, headers, listener, errorListener);
        String uuid = UUID.randomUUID().toString();
        request.setTag(uuid);
        requestQueue.add(request);

        return uuid;
    }

    public String getDevices(Response.Listener<List<Device>> listener, Response.ErrorListener errorListener) {
        String url = URL +"devices";
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        GsonRequest<Object, List<Device>> request =
                new GsonRequest<>(Request.Method.GET, url, null, "result", new TypeToken<List<Device>>(){}, null, listener, errorListener);
        String uuid = UUID.randomUUID().toString();
        request.setTag(uuid);
        requestQueue.add(request);

        return uuid;
    }

    public String getDevice(String id, Response.Listener<Device> listener, Response.ErrorListener errorListener) {
        String url = URL +"devices/"+ id ;
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        GsonRequest<Object, Device> request =
                new GsonRequest<>(Request.Method.GET, url, null, "result", new TypeToken<Device>(){}, null, listener, errorListener);
        String uuid = UUID.randomUUID().toString();
        request.setTag(uuid);
        requestQueue.add(request);

        return uuid;
    }

    public String deleteDevice(Device device, Response.Listener<Boolean> listener, Response.ErrorListener errorListener) {
        String url = URL + "devices/" + device.getId();
        GsonRequest<Object, Boolean> request =
                new GsonRequest<>(Request.Method.DELETE, url, null, "result", new TypeToken<Boolean>(){}, null, listener, errorListener);
        String uuid = UUID.randomUUID().toString();
        request.setTag(uuid);
        requestQueue.add(request);

        return uuid;
    }

    public String updateDevice(Device device, Response.Listener<Boolean> listener, Response.ErrorListener errorListener) {
        String url = URL + "devices/" + device.getId();
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        GsonRequest<Device, Boolean> request =
                new GsonRequest<>(Request.Method.PUT, url, device, "result", new TypeToken<Boolean>(){}, headers, listener, errorListener);
        String uuid = UUID.randomUUID().toString();
        request.setTag(uuid);
        requestQueue.add(request);

        return uuid;
    }

    public String getRoomDevices(Room room, Response.Listener<List<Device>> listener, Response.ErrorListener errorListener) {
        String url = URL + "rooms/" + room.getId() + "/devices/";
        GsonRequest<Object, List<Device>> request =
                new GsonRequest<>(Request.Method.GET, url, null, "result", new TypeToken<List<Device>>(){}, null, listener, errorListener);
        String uuid = UUID.randomUUID().toString();
        request.setTag(uuid);
        requestQueue.add(request);
        return uuid;
    }

    ///////////////     ROUTINES     ////////////////

    public String getRoutine(String id, Response.Listener<Routine> listener, Response.ErrorListener errorListener) {
        String url = URL +"routines/"+id;
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        GsonRequest<Object, Routine> request =
                new GsonRequest<>(Request.Method.GET, url, null, "result", new TypeToken<Routine>(){}, null, listener, errorListener);
        String uuid = UUID.randomUUID().toString();
        request.setTag(uuid);
        requestQueue.add(request);

        return uuid;
    }

    public String getRoutines(Response.Listener<List<Routine>> listener, Response.ErrorListener errorListener) {
        String url = URL + "routines/";
        GsonRequest<Object, List<Routine>> request =
                new GsonRequest<>(Request.Method.GET, url, null, "result", new TypeToken<List<Routine>>(){}, null, listener, errorListener);
        String uuid = UUID.randomUUID().toString();
        request.setTag(uuid);
        requestQueue.add(request);
        return uuid;
    }

    public String createRoutine(Routine routine,  Response.Listener<Routine> listener, Response.ErrorListener errorListener) {
        String url = URL + "routines";
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        GsonRequest<Routine, Routine> request =
                new GsonRequest<>(Request.Method.POST, url, routine, "result", new TypeToken<Routine>(){}, headers, listener, errorListener);
        String uuid = UUID.randomUUID().toString();
        request.setTag(uuid);
        requestQueue.add(request);

        return uuid;
    }

    public String updateRoutine(Routine routine, Response.Listener<Boolean> listener, Response.ErrorListener errorListener) {
        String url = URL + "routines/" + routine.getId();
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        GsonRequest<Routine, Boolean> request =
                new GsonRequest<>(Request.Method.PUT, url, routine, "result", new TypeToken<Boolean>(){}, headers, listener, errorListener);
        String uuid = UUID.randomUUID().toString();
        request.setTag(uuid);
        requestQueue.add(request);

        return uuid;
    }

    public String deleteRoutine(Routine routine, Response.Listener<Boolean> listener, Response.ErrorListener errorListener) {
        String url = URL + "routines/" + routine.getId();
        GsonRequest<Object, Boolean> request =
                new GsonRequest<>(Request.Method.DELETE, url, null, "result", new TypeToken<Boolean>(){}, null, listener, errorListener);
        String uuid = UUID.randomUUID().toString();
        request.setTag(uuid);
        requestQueue.add(request);

        return uuid;
    }

    public String executeRoutine(Routine routine, Response.Listener<Object> listener, Response.ErrorListener errorListener) {
        String url = URL + "routines/" + routine.getId() + "/execute";
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        GsonRequest<Object, Object> request =
                new GsonRequest<>(Request.Method.PUT, url, null, null, new TypeToken<Object>(){}, headers, listener, errorListener);
        String uuid = UUID.randomUUID().toString();
        request.setTag(uuid);
        requestQueue.add(request);

        return uuid;
    }

    ////////////        STATES      ////////////////////////

    public String runAction(Action action, Response.Listener<Object> listener, Response.ErrorListener errorListener) {
        String url = URL + "devices/" + action.getDeviceId() +"/" + action.getActionName();
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        GsonRequest<List<String>, Object> request =
                new GsonRequest<>(Request.Method.PUT, url, action.getParams(), null, new TypeToken<Object>(){}, headers, listener, errorListener);
        String uuid = UUID.randomUUID().toString();
        request.setTag(uuid);
        requestQueue.add(request);

        return uuid;
    }

    public String getStateAc(Device device, Response.Listener<StateAC> listener, Response.ErrorListener errorListener) {
        String url = URL + "devices/" + device.getId() +"/" + "getState";
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        GsonRequest<Object, StateAC> request =
                new GsonRequest<>(Request.Method.PUT, url, null , "result", new TypeToken<StateAC>(){}, headers, listener, errorListener);
        String uuid = UUID.randomUUID().toString();
        request.setTag(uuid);
        requestQueue.add(request);

        return uuid;
    }

    public String getStateAlarm(Device device, Response.Listener<StateAlarm> listener, Response.ErrorListener errorListener) {
        String url = URL + "devices/" + device.getId() +"/" + "getState";
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        GsonRequest<Object, StateAlarm> request =
                new GsonRequest<>(Request.Method.PUT, url, null , "result", new TypeToken<StateAlarm>(){}, headers, listener, errorListener);
        String uuid = UUID.randomUUID().toString();
        request.setTag(uuid);
        requestQueue.add(request);

        return uuid;
    }

    public String getStateBlinds(Device device, Response.Listener<StateBlinds> listener, Response.ErrorListener errorListener) {
        String url = URL + "devices/" + device.getId() +"/" + "getState";
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        GsonRequest<Object, StateBlinds> request =
                new GsonRequest<>(Request.Method.PUT, url, null , "result", new TypeToken<StateBlinds>(){}, headers, listener, errorListener);
        String uuid = UUID.randomUUID().toString();
        request.setTag(uuid);
        requestQueue.add(request);

        return uuid;
    }

    public String getStateDoor(Device device, Response.Listener<StateDoor> listener, Response.ErrorListener errorListener) {
        String url = URL + "devices/" + device.getId() +"/" + "getState";
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        GsonRequest<Object, StateDoor> request =
                new GsonRequest<>(Request.Method.PUT, url, null , "result", new TypeToken<StateDoor>(){}, headers, listener, errorListener);
        String uuid = UUID.randomUUID().toString();
        request.setTag(uuid);
        requestQueue.add(request);

        return uuid;
    }


    public String getStateLamp(Device device, Response.Listener<StateLamp> listener, Response.ErrorListener errorListener) {
        String url = URL + "devices/" + device.getId() +"/" + "getState";
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        GsonRequest<Object, StateLamp> request =
                new GsonRequest<>(Request.Method.PUT, url, null , "result", new TypeToken<StateLamp>(){}, headers, listener, errorListener);
        String uuid = UUID.randomUUID().toString();
        request.setTag(uuid);
        requestQueue.add(request);

        return uuid;
    }

    public String getStateStereo(Device device, Response.Listener<StateStereo> listener, Response.ErrorListener errorListener) {
        String url = URL + "devices/" + device.getId() +"/" + "getState";
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        GsonRequest<Object, StateStereo> request =
                new GsonRequest<>(Request.Method.PUT, url, null , "result", new TypeToken<StateStereo>(){}, headers, listener, errorListener);
        String uuid = UUID.randomUUID().toString();
        request.setTag(uuid);
        requestQueue.add(request);

        return uuid;
    }

    public String getStateVacuum(Device device, Response.Listener<StateVacuum> listener, Response.ErrorListener errorListener) {
        String url = URL + "devices/" + device.getId() +"/" + "getState";
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        GsonRequest<Object, StateVacuum> request =
                new GsonRequest<>(Request.Method.PUT, url, null , "result", new TypeToken<StateVacuum>(){}, headers, listener, errorListener);
        String uuid = UUID.randomUUID().toString();
        request.setTag(uuid);
        requestQueue.add(request);

        return uuid;
    }

/////////////////////////////////////

    public String getDeviceEvents(Device device, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        String url = URL +"devices/"+device.getId()+"/events";
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        GsonRequest<Object, String> request =
                new GsonRequest<>(Request.Method.GET, url, null, "result", new TypeToken<String>(){}, null, listener, errorListener);
        String uuid = UUID.randomUUID().toString();
        request.setTag(uuid);
        requestQueue.add(request);

        return uuid;
    }

    public void cancelRequest(String uuid) {
        if ((uuid != null) && (requestQueue != null)) {
            requestQueue.cancelAll(uuid);
        }
    }
}