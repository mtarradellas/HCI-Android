package com.example.smarthome.ui.DevicesViews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;

import com.example.smarthome.Device;
import com.example.smarthome.R;
import com.example.smarthome.ui.Favourites.FavouritesRecyclerViewAdapter;
import com.example.smarthome.ui.Home.HomeRecyclerViewAdapter;

import java.util.ArrayList;

public class LampActivity extends AppCompatActivity {

    private Device lamp;
    private TextView lampBackTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lamp);

        Intent intent = getIntent();
        lamp = intent.getParcelableExtra(FavouritesRecyclerViewAdapter.EXTRA_DEVICE);

        lampBackTextView = findViewById(R.id.lampBackTextView);

        lampBackTextView.setText(lamp.getName());

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(lamp.getName());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }
}
