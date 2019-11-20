package com.example.smarthome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.smarthome.API.Api;
import com.example.smarthome.ui.FavouritesFragment;
import com.example.smarthome.ui.HomeFragment;
import com.example.smarthome.ui.RoutinesFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.Console;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener,
        HomeFragment.HomeFragmentListener {

    HomeFragment homeFragment;
    RoutinesFragment routinesFragment;
    FavouritesFragment favouritesFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        homeFragment = new HomeFragment();
        favouritesFragment = new FavouritesFragment();
        routinesFragment = new RoutinesFragment();

        if(savedInstanceState == null) {
            bottomNavigationView.getMenu().getItem(1).setChecked(true);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, homeFragment)
                    .commit();
        }
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

    @Override
    public void onHomeAddClick(String string) {
        final Room room = new Room("francas room", null);
        Api.getInstance(this.getApplicationContext()).addRoom(room, new Response.Listener<Room>() {
            @Override
            public void onResponse(Room response) {
                homeFragment.updateTextView(room.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                homeFragment.updateTextView("error");
            }
        });
    }

    @Override
    public void onHomeGetClick(String string) {
        Api.getInstance(this.getApplicationContext()).getRooms(new Response.Listener<ArrayList<Room>>() {
            @Override
            public void onResponse(ArrayList<Room> response) {
                homeFragment.updateTextView(TextUtils.join("-", response));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                homeFragment.updateTextView("ERROR");
            }
        });
    }
}
