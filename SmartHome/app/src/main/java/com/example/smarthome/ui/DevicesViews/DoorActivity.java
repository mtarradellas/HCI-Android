package com.example.smarthome.ui.DevicesViews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;

import com.example.smarthome.Device;
import com.example.smarthome.MainActivity;
import com.example.smarthome.R;
import com.example.smarthome.ui.Favourites.FavouritesRecyclerViewAdapter;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;
import android.widget.ToggleButton;

public class DoorActivity extends AppCompatActivity {

    private Device door;
    private TextView doorBackTextView;
    private ToggleButton doorOpenToggle;
    private ToggleButton doorLockToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_door);

        Intent intent = getIntent();
        door = intent.getParcelableExtra(MainActivity.EXTRA_DEVICE);

        doorBackTextView = findViewById(R.id.doorBackTextView);
        doorBackTextView.setText("");

        doorOpenToggle = findViewById(R.id.doorOpenToggle);
        doorLockToggle = findViewById(R.id.doorLockToggle);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(door.getName());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }
}
