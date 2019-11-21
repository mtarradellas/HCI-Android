package com.example.smarthome.API;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.example.smarthome.Action;
import com.example.smarthome.Device;
import com.example.smarthome.Room;
import com.example.smarthome.Routine;
import com.example.smarthome.DeviceStates.StateAC;
import com.example.smarthome.DeviceStates.StateAlarm;
import com.example.smarthome.DeviceStates.StateBlinds;
import com.example.smarthome.DeviceStates.StateDoor;
import com.example.smarthome.DeviceStates.StateLamp;
import com.example.smarthome.DeviceStates.StateStereo;
import com.example.smarthome.DeviceStates.StateVacuum;
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
    //tested
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
        //TESTED
        return uuid;
    }

    public String deleteRoom(String id, Response.Listener<Boolean> listener, Response.ErrorListener errorListener) {
        String url = URL + "rooms/" + id;
        GsonRequest<Object, Boolean> request =
                new GsonRequest<>(Request.Method.DELETE, url, null, "result", new TypeToken<Boolean>(){}, null, listener, errorListener);
        String uuid = UUID.randomUUID().toString();
        request.setTag(uuid);
        requestQueue.add(request);
        //tested
        return uuid;
    }

    public String getRoom(String id, Response.Listener<Room> listener, Response.ErrorListener errorListener) {
        String url = URL + "rooms/" + id;
        GsonRequest<Object, Room> request =
                new GsonRequest<>(Request.Method.GET, url, null, "result", new TypeToken<Room>(){}, null, listener, errorListener);
        String uuid = UUID.randomUUID().toString();
        request.setTag(uuid);
        requestQueue.add(request);
        //tested
        return uuid;
    }

    public String getRooms(Response.Listener<ArrayList<Room>> listener, Response.ErrorListener errorListener) {
        String url = URL + "rooms/";
        GsonRequest<Object, ArrayList<Room>> request =
                new GsonRequest<>(Request.Method.GET, url, null, "result", new TypeToken<ArrayList<Room>>(){}, null, listener, errorListener);
        String uuid = UUID.randomUUID().toString();
        request.setTag(uuid);
        requestQueue.add(request);
        //tested
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
        //tested
        return uuid;
    }

    public String getDevices(Response.Listener<ArrayList<Device>> listener, Response.ErrorListener errorListener) {
        String url = URL +"devices/";
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        GsonRequest<Object, ArrayList<Device>> request =
                new GsonRequest<>(Request.Method.GET, url, null, "devices", new TypeToken<ArrayList<Device>>(){}, null, listener, errorListener);
        String uuid = UUID.randomUUID().toString();
        request.setTag(uuid);
        requestQueue.add(request);
    //tested
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
        //tested
        return uuid;
    }

    public String deleteDevice(String deviceId, Response.Listener<Boolean> listener, Response.ErrorListener errorListener) {
        String url = URL + "devices/" + deviceId;
        GsonRequest<Object, Boolean> request =
                new GsonRequest<>(Request.Method.DELETE, url, null, "result", new TypeToken<Boolean>(){}, null, listener, errorListener);
        String uuid = UUID.randomUUID().toString();
        request.setTag(uuid);
        requestQueue.add(request);
        //tested
        return uuid;
    }

    public String updateDevice(Device device, Response.Listener<Boolean> listener, Response.ErrorListener errorListener) {
        String url = URL + "devices/" + device.getId();
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        Device apiDevice = new Device(device.getId(), device.getName(), null, device.getMeta());
        GsonRequest<Device, Boolean> request =
                new GsonRequest<>(Request.Method.PUT, url, apiDevice, "result", new TypeToken<Boolean>(){}, headers, listener, errorListener);
        String uuid = UUID.randomUUID().toString();
        request.setTag(uuid);
        requestQueue.add(request);
        //tested
        return uuid;
    }


    public String getRoomDevices(String roomId, Response.Listener<ArrayList<Device>> listener, Response.ErrorListener errorListener) {
        String url = URL + "rooms/" + roomId + "/devices/";
        GsonRequest<Object, ArrayList<Device>> request =
                new GsonRequest<>(Request.Method.GET, url, null, "result", new TypeToken<ArrayList<Device>>(){}, null, listener, errorListener);
        String uuid = UUID.randomUUID().toString();
        request.setTag(uuid);
        requestQueue.add(request);
        //tested
        return uuid;
    }

    ///////////////     ROUTINES     ////////////////

    public String getRoutine(String id, Response.Listener<Routine> listener, Response.ErrorListener errorListener) {
        String url = URL +"routines/"+id;
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        GsonRequest<Object, Routine> request =
                new GsonRequest<>(Request.Method.GET, url, null, "routine", new TypeToken<Routine>(){}, null, listener, errorListener);
        String uuid = UUID.randomUUID().toString();
        request.setTag(uuid);
        requestQueue.add(request);
        //tested
        return uuid;
    }

    public String getRoutines(Response.Listener<ArrayList<Routine>> listener, Response.ErrorListener errorListener) {
        String url = URL + "routines/";
        GsonRequest<Object, ArrayList<Routine>> request =
                new GsonRequest<>(Request.Method.GET, url, null, "result", new TypeToken<ArrayList<Routine>>(){}, null, listener, errorListener);
        String uuid = UUID.randomUUID().toString();
        request.setTag(uuid);
        requestQueue.add(request);
        //tested
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
    //tested
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
        //tested
        return uuid;
    }

    public String deleteRoutine(String routineId, Response.Listener<Boolean> listener, Response.ErrorListener errorListener) {
        String url = URL + "routines/" + routineId;
        GsonRequest<Object, Boolean> request =
                new GsonRequest<>(Request.Method.DELETE, url, null, "result", new TypeToken<Boolean>(){}, null, listener, errorListener);
        String uuid = UUID.randomUUID().toString();
        request.setTag(uuid);
        requestQueue.add(request);
        //tested
        return uuid;
    }

    //returns boolean array, one boolean for each action in the routine, true if executed, false if not
    public String executeRoutine(String routineId, Response.Listener<ArrayList<Boolean>> listener, Response.ErrorListener errorListener) {
        String url = URL + "routines/" + routineId + "/execute";
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        GsonRequest<Object, ArrayList<Boolean>> request =
                new GsonRequest<>(Request.Method.PUT, url, null, "result", new TypeToken<ArrayList<Boolean>>(){}, headers, listener, errorListener);
        String uuid = UUID.randomUUID().toString();
        request.setTag(uuid);
        requestQueue.add(request);
        //tested
        return uuid;
    }

    ////////////        STATES      ////////////////////////

    public String getStateAc(String deviceId, Response.Listener<StateAC> listener, Response.ErrorListener errorListener) {
        String url = URL + "devices/" + deviceId +"/" + "state";
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        GsonRequest<Object, StateAC> request =
                new GsonRequest<>(Request.Method.GET, url, null , "result", new TypeToken<StateAC>(){}, headers, listener, errorListener);
        String uuid = UUID.randomUUID().toString();
        request.setTag(uuid);
        requestQueue.add(request);
        //tested
        return uuid;
    }

    public String getStateAlarm(String deviceId, Response.Listener<StateAlarm> listener, Response.ErrorListener errorListener) {
        String url = URL + "devices/" + deviceId +"/" + "state";
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        GsonRequest<Object, StateAlarm> request =
                new GsonRequest<>(Request.Method.GET, url, null , "result", new TypeToken<StateAlarm>(){}, headers, listener, errorListener);
        String uuid = UUID.randomUUID().toString();
        request.setTag(uuid);
        requestQueue.add(request);
    //tested
        return uuid;
    }

    public String getStateBlinds(String deviceId, Response.Listener<StateBlinds> listener, Response.ErrorListener errorListener) {
        String url = URL + "devices/" + deviceId +"/" + "state";
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        GsonRequest<Object, StateBlinds> request =
                new GsonRequest<>(Request.Method.GET, url, null , "result", new TypeToken<StateBlinds>(){}, headers, listener, errorListener);
        String uuid = UUID.randomUUID().toString();
        request.setTag(uuid);
        requestQueue.add(request);
        //tested
        return uuid;
    }

    public String getStateDoor(String deviceId, Response.Listener<StateDoor> listener, Response.ErrorListener errorListener) {
        String url = URL + "devices/" + deviceId +"/" + "state";
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        GsonRequest<Object, StateDoor> request =
                new GsonRequest<>(Request.Method.GET, url, null , "result", new TypeToken<StateDoor>(){}, headers, listener, errorListener);
        String uuid = UUID.randomUUID().toString();
        request.setTag(uuid);
        requestQueue.add(request);
        //tested
        return uuid;
    }


    public String getStateLamp(String deviceId, Response.Listener<StateLamp> listener, Response.ErrorListener errorListener) {
        String url = URL + "devices/" + deviceId +"/" + "state";
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        GsonRequest<Object, StateLamp> request =
                new GsonRequest<>(Request.Method.GET, url, null , "result", new TypeToken<StateLamp>(){}, headers, listener, errorListener);
        String uuid = UUID.randomUUID().toString();
        request.setTag(uuid);
        requestQueue.add(request);
        //tested
        return uuid;
    }

    public String getStateStereo(String deviceId, Response.Listener<StateStereo> listener, Response.ErrorListener errorListener) {
        String url = URL + "devices/" + deviceId +"/" + "state";
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        GsonRequest<Object, StateStereo> request =
                new GsonRequest<>(Request.Method.GET, url, null , "result", new TypeToken<StateStereo>(){}, headers, listener, errorListener);
        String uuid = UUID.randomUUID().toString();
        request.setTag(uuid);
        requestQueue.add(request);
        //tested
        return uuid;
    }

    public String getStateVacuum(String deviceId, Response.Listener<StateVacuum> listener, Response.ErrorListener errorListener) {
        String url = URL + "devices/" + deviceId +"/" + "state";
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        GsonRequest<Object, StateVacuum> request =
                new GsonRequest<>(Request.Method.GET, url, null , "result", new TypeToken<StateVacuum>(){}, headers, listener, errorListener);
        String uuid = UUID.randomUUID().toString();
        request.setTag(uuid);
        requestQueue.add(request);
        //tested
        return uuid;
    }

///////////////////////////////////// EVENTS ////////////////////////////////

    //returns true if executed correctly or false otherwise. Take into account that if a lamp is turned on and the action tuns on the lamp, it will return false
    public String runAction(Action action, Response.Listener<Boolean> listener, Response.ErrorListener errorListener) {
        String url = URL + "devices/" + action.getDeviceId().getId() +"/" + action.getActionName();
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        GsonRequest<List<String>, Boolean> request =
                new GsonRequest<>(Request.Method.PUT, url, action.getParams(), "result", new TypeToken<Boolean>(){}, headers, listener, errorListener);
        String uuid = UUID.randomUUID().toString();
        request.setTag(uuid);
        requestQueue.add(request);
        //tested
        return uuid;
    }

//    public String getDeviceEvents(String deviceId, Response.Listener<String> listener, Response.ErrorListener errorListener) {
//        String url = URL +"devices/"+deviceId+"/events";
//        Map<String, String> headers = new HashMap<>();
//        headers.put("Content-Type", "application/json");
//        GsonRequest<Object, String> request =
//                new GsonRequest<>(Request.Method.GET, url, null, "result", new TypeToken<String>(){}, null, listener, errorListener);
//        String uuid = UUID.randomUUID().toString();
//        request.setTag(uuid);
//        requestQueue.add(request);
//        //what the fuck.....
//        return uuid;
//    }

    //////////////////////////////////////

    public void cancelRequest(String uuid) {
        if ((uuid != null) && (requestQueue != null)) {
            requestQueue.cancelAll(uuid);
        }
    }
}