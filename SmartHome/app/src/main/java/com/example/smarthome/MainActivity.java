package com.example.smarthome;

import android.content.Intent;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.smarthome.API.Api;
import com.example.smarthome.ui.Favourites.FavouritesFragment;
import com.example.smarthome.ui.Home.HomeFragment;
import com.example.smarthome.ui.Routines.RoutinesFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {

    public static final String EXTRA_DEVICE = "com.example.smarthome.EXTRA_DEVICE";

    private HomeFragment homeFragment;
    private RoutinesFragment routinesFragment;
    private FavouritesFragment favouritesFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeFragment = new HomeFragment();
        favouritesFragment = new FavouritesFragment();
        routinesFragment = new RoutinesFragment();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        createNotificationChannel();
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent alarmIntent = new Intent(this, MyBroadCastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, 0);
        long interval;
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String intervalPreference = sharedPreferences.getString("interval_preference","1 min");

        if(intervalPreference.equals("1 min") ){
            interval = 60 * 1000;
        }else if(intervalPreference.equals("30 min")){
            interval = 30 * 60 * 1000;
        }else{
            interval = 60 * 60 * 1000;
        }
        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime(),
                interval, pendingIntent);

        if (savedInstanceState == null) {
            bottomNavigationView.getMenu().getItem(1).setChecked(true);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, homeFragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            case R.id.about:
                Intent about = new Intent(this.getApplicationContext(), AboutUsActivity.class);
                startActivity(about);
                Toast.makeText(this, "About", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        //Fragment fragment;

        int id = menuItem.getItemId();
        switch (id) {
            case R.id.nav_routines:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, routinesFragment)
                        .commit();
                break;
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, homeFragment)
                        .commit();
                break;
            case R.id.nav_favourites:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, favouritesFragment)
                        .commit();
                break;
        }
        return true;
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "The channel name"; // getString(R.string.channel_name);
            String description = "The channel description"; // getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("smarthome_notification_channel", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

    }

    private void startAlarm(AlarmManager alarmManager, PendingIntent pendingIntent) {

        alarmManager.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, 0, pendingIntent);


    }
}
