//package com.example.smarthome;
//
//import android.annotation.SuppressLint;
//import android.app.Notification;
//import android.app.NotificationManager;
//import android.app.PendingIntent;
//import android.app.TaskStackBuilder;
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.graphics.BitmapFactory;
//import android.media.RingtoneManager;
//import android.net.Uri;
//import android.preference.PreferenceManager;
//import android.util.Log;
//import android.widget.Toast;
//
//import androidx.core.app.NotificationCompat;
//import androidx.core.content.ContextCompat;
//
//import com.android.volley.Request;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.example.smarthome.API.Api;
//import com.example.smarthome.API.VolleySingleton;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class NotificationBroadcastReceiver extends BroadcastReceiver {
//    private List<String> deviceTypes;
//    private Context context;
//    private Map<String, Integer> existingIds;
//    private int NOTIFICATION_ID = 0;
//
//    @Override
//    public void onReceive(Context context, Intent intent) {
//        Log.i("MyLog", "En el recieve del notification");
//        existingIds = new HashMap<>();
//        this.context = context;
//        getAllowedDevices();
//        getEvents();
//    }
//
//
//
//    private void getEvents() {
//        Api.getInstance(context).getDevices((response) -> {
//            for (Device device : response) {
//                boolean aux = true;
//                for (int i = 0; i < deviceTypes.size() && aux; i++) {
//                    class EventsResponse implements Response.Listener<String> {
//                        private Device device;
//
//                        public EventsResponse(Device device) {
//                            this.device = device;
//                        }
//
//                        @Override
//                        public void onResponse(String response) {
//                            Log.i("MyLog", response);
//                            sendNotification(response, device.getName(), device.getTypeId().toString());
//                        }
//                    }
//                    String apiUrl = Api.getInstance(context).getUrl();
//                    StringRequest stringRequest = new StringRequest(Request.Method.GET, apiUrl + "devices/" + device.getId() + "/events",
//                            new EventsResponse(device), error -> Log.d("NOTIFERROR2", error.toString()));
//                    VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
//                }
//            }
//        }, error -> Log.d("NOTIFERROR1", error.toString()));
//    }
//
//
//    private void sendNotification(String response, String name, String id) {
//        String[] events = response.split("event");
//        String[] aux;
//        String event = "";
//        for (int i = 1; i < events.length; i++) {
//            aux = events[i].split(",");
//            aux[0] = aux[0].replace(":", "");
//            aux[0] = aux[0].replace(" ", "");
//            aux[0] = aux[0].replace("\"", "");
//            event = event + aux[0] + " ";
//        }
//        Toast.makeText(context, name + " has changed its state: " + event, Toast.LENGTH_LONG).show();
//        Intent notificationIntent = new Intent(this.context, MainActivity.class);
//
//
//        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this.context);
//        stackBuilder.addParentStack(MainActivity.class);
//        stackBuilder.addNextIntent(notificationIntent);
//
//        final PendingIntent contentIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
//
//        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "smarthome_notification_channel")
//                .setContentTitle(context.getResources().getString(R.string.ChangeMessage))
//                .setContentText(name + " " + event)
//                .setSmallIcon(R.drawable.lenia_sga)
//                .setColor(ContextCompat.getColor(context, R.color.blue))
//                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.lenia_sga))
//                .setSound(defaultSoundUri)
//                .setContentIntent(contentIntent)
//                .setPriority(Notification.PRIORITY_DEFAULT)
//                .setAutoCancel(true);
//
//
//        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//        Integer currId = existingIds.get(name);
//        if (currId == null) {
//            currId = NOTIFICATION_ID++;
//        }
//        notificationManager.notify(currId, builder.build());
//    }
//}
