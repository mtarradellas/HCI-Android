package com.example.smarthome.ui.DevicesViews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.smarthome.API.Api;
import com.example.smarthome.Device;
import com.example.smarthome.DeviceStates.StateLamp;
import com.example.smarthome.MainActivity;
import com.example.smarthome.R;
import com.example.smarthome.ui.Favourites.FavouritesRecyclerViewAdapter;
import com.example.smarthome.ui.Home.HomeRecyclerViewAdapter;

import java.util.ArrayList;

public class LampActivity extends AppCompatActivity {

    private Device lamp;
    private StateLamp stateLamp;

    private TextView lampBackTextView;
    private Switch aSwitch;
    private SeekBar seekBar;
    private RadioButton white;
    private RadioButton red;
    private RadioButton green;
    private RadioButton blue;
    private RadioButton yellow;
    private RadioButton pink;
    private RadioButton purple;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lamp);

        Intent intent = getIntent();
        lamp = intent.getParcelableExtra(MainActivity.EXTRA_DEVICE);

        lampBackTextView = findViewById(R.id.lampBackTextView);
        lampBackTextView.setText(R.string.loading);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(lamp.getName());

        aSwitch = findViewById(R.id.lampSwitch);
        seekBar = findViewById(R.id.lampDimmerSeekBar);

        white = findViewById(R.id.lampWhite);
        red = findViewById(R.id.lampRed);
        green = findViewById(R.id.lampGreen);
        blue = findViewById(R.id.lampBlue);
        yellow = findViewById(R.id.lampYellow);
        pink = findViewById(R.id.lampPink);
        purple = findViewById(R.id.lampPurple);

        stateLamp = new StateLamp();
        Api.getInstance(this.getApplicationContext()).getStateLamp(lamp.getId(), new Response.Listener<StateLamp>() {
            @Override
            public void onResponse(StateLamp response) {
                stateLamp.setBrightness(response.getBrightness());
                stateLamp.setColor(response.getColor());
                stateLamp.setStatus(response.getStatus());
                loadScreen();
                lampBackTextView.setText("");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                lampBackTextView.setText(R.string.device_no_connection);
            }
        });

    }

    private void loadScreen() {
        aSwitch.setChecked(stateLamp.getStatus().compareToIgnoreCase("on") == 0);
        aSwitch.setText(stateLamp.getStatus());
        if (stateLamp.getColor().equalsIgnoreCase("FFEB3B")) {
            yellow.toggle();
        } else if (stateLamp.getColor().equalsIgnoreCase("FF0000")) {
            red.toggle();
        } else if (stateLamp.getColor().equalsIgnoreCase("8BC34A")) {
            green.toggle();
        } else if (stateLamp.getColor().equalsIgnoreCase("#3F51B5")) {
            blue.toggle();
        } else if (stateLamp.getColor().equalsIgnoreCase("#8F1AA3")) {
            purple.toggle();
        } else if (stateLamp.getColor().equalsIgnoreCase("FF2EE6")) {
            pink.toggle();
        } else {
            white.toggle();
        }
        seekBar.setProgress(stateLamp.getBrightness());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }
}
