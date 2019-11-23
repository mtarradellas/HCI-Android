package com.example.smarthome.ui.DevicesViews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;

import com.example.smarthome.Device;
import com.example.smarthome.R;
import com.example.smarthome.ui.Favourites.FavouritesRecyclerViewAdapter;

import android.os.Bundle;
import android.widget.TextView;

public class DoorActivity extends AppCompatActivity {

    private Device door;
    private TextView doorBackTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_door);

        Intent intent = getIntent();
        door = intent.getParcelableExtra(FavouritesRecyclerViewAdapter.EXTRA_DEVICE);

        doorBackTextView = findViewById(R.id.doorBackTextView);
        doorBackTextView.setText("");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.about_us_title);
    }
}
