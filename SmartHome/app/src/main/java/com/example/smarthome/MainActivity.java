package com.example.smarthome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.smarthome.ui.Favourites.FavouritesFragment;
import com.example.smarthome.ui.Home.HomeFragment;
import com.example.smarthome.ui.Routines.RoutinesFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener,
        HomeFragment.HomeFragmentListener {

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


        if(savedInstanceState == null) {
            bottomNavigationView.getMenu().getItem(1).setChecked(true);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, homeFragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.icon1:
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.icon2:
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
}
