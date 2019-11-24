package com.example.smarthome;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.smarthome.API.Api;
import com.example.smarthome.API.VolleySingleton;

import java.util.HashMap;
import java.util.Map;

public class MyBroadCastReceiver extends BroadcastReceiver {
    Map<String, Boolean> typesMap = new HashMap<>();
    private int NOTIFICATION_ID = 0;

    @Override
    public void onReceive(Context context, Intent intent) {
        getAllowedDevices(context);
        getEvents(context);
    }

    private void getAllowedDevices(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        boolean ac = sharedPreferences.getBoolean("ac_preference", false);
        boolean blind = sharedPreferences.getBoolean("blinds_preference", false);
        boolean door = sharedPreferences.getBoolean("door_preference", false);
        boolean lamp = sharedPreferences.getBoolean("lamp_preference", false);
        boolean speaker = sharedPreferences.getBoolean("speaker_preference", false);
        boolean vacuum = sharedPreferences.getBoolean("vacuum_preference", false);
        boolean alarm = sharedPreferences.getBoolean("alarm_preference", false);
        typesMap.put("li6cbv5sdlatti0j", ac);
        typesMap.put("eu0v2xgprrhhg41g", blind);
        typesMap.put("lsf78ly0eqrjbz91", door);
        typesMap.put("go46xmbqeomjrsjr", lamp);
        typesMap.put("c89b94e8581855bc", speaker);
        typesMap.put("ofglvd9gqX8yfl3l", vacuum);
        typesMap.put("mxztsyjzsrq7iaqc", alarm);



        Log.i("MyLog", "selecting desired devices");
    }

    private void getEvents(Context context) {
        Api.getInstance(context).getDevices((response) -> {
            for (Device device : response) {
                for (Map.Entry<String, Boolean> entry : typesMap.entrySet()) {
                    if (entry.getKey().equals(device.getTypeId().toString()) && entry.getValue() ) {
                        class EventsResponse implements Response.Listener<String> {
                            private Device device;

                            private EventsResponse(Device device) {
                                this.device = device;
                            }

                            @Override
                            public void onResponse(String response) {

                                if (!response.isEmpty())
                                    sendNotification(context, response, device.getName());
                            }
                        }
                        String apiUrl = Api.getInstance(context).getUrl();
                        StringRequest stringRequest = new StringRequest(Request.Method.GET, apiUrl + "devices/" + device.getId() + "/events",
                                new EventsResponse(device), error -> Log.d("NOTIFERROR2", error.toString()));
                        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
                    }
                }
            }
        }, error -> Log.d("NOTIFERROR1", error.toString()));
    }

    private void sendNotification(Context context, String response, String name) {
        String event = "";
        String[] events = response.split("\n");

        for (String str : events) {
            if (str.contains("newStatus")) {
                String[] line = str.split("newStatus");
                for (String s : line) {
                    String[] words = s.split("\"");

                    for (String word : words) {
                        if (word.matches("[a-zA-Z0-9]*")) {
                            event = word;
                        }
                    }
                }
            }
        }
        String contentText = name + context.getResources().getString(R.string.ChangeMessage2) + ((!event.isEmpty()) ? ": " + event : "");
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "smarthome_notification_channel")
                .setContentTitle(context.getResources().getString(R.string.ChangeMessage))
                .setContentText(contentText)
                .setSmallIcon(R.drawable.lenia_sga)
                .setColor(ContextCompat.getColor(context, R.color.colorBottomNavBg))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(NOTIFICATION_ID++, builder.build());
    }
}



