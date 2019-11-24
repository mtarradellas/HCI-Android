package com.example.smarthome.ui.DevicesViews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;

import com.example.smarthome.API.Api;
import com.example.smarthome.Device;
import com.example.smarthome.DeviceStates.StateDoor;
import com.example.smarthome.MainActivity;
import com.example.smarthome.R;
import com.example.smarthome.ui.Favourites.FavouritesRecyclerViewAdapter;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;
import android.widget.Toast;
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
        StateDoor stateDoor = new StateDoor();
        Api.getInstance(this.getApplicationContext()).getStateDoor(door.getId(), response -> {
            stateDoor.setStatus(response.getStatus());
            doorOpenToggle.setChecked(!stateDoor.getStatus().contains("clos"));
            stateDoor.setLock(response.getLock());
            doorLockToggle.setChecked(stateDoor.getLock().contains("loc"));
            doorBackTextView.setText("");
        }, error -> doorBackTextView.setText(R.string.device_no_connection));

        doorOpenToggle.setOnClickListener(t -> {
            Api.getInstance(this.getApplicationContext()).doorOpenSwitch(door.getId(), doorOpenToggle.isChecked(), response -> {
                Log.i("MyLog", response.toString());
                if (!response) {
                    doorOpenToggle.toggle();
                }
            }, error -> {
                doorOpenToggle.toggle();
                Toast.makeText(this, "Something went wrong when modifying this door", Toast.LENGTH_LONG).show();
            });
        });

        doorLockToggle.setOnClickListener(t -> {
            Api.getInstance(this.getApplicationContext()).doorLockSwitch(door.getId(), !doorLockToggle.isChecked(), response -> {
                Log.i("MyLog", response.toString());
                if (!response) {
                    doorLockToggle.toggle();
                }
            }, error -> {
                doorLockToggle.toggle();
                Toast.makeText(this, "Something went wrong when modifying the lock this door", Toast.LENGTH_LONG).show();
            });
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }
}
