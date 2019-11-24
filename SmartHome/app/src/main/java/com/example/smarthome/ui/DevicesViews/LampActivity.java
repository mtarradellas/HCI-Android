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
import android.widget.Toast;


import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.smarthome.API.Api;
import com.example.smarthome.Action;
import com.example.smarthome.Device;
import com.example.smarthome.DeviceId;
import com.example.smarthome.DeviceStates.StateLamp;
import com.example.smarthome.MainActivity;
import com.example.smarthome.Meta;
import com.example.smarthome.R;
import com.example.smarthome.ui.Favourites.FavouritesRecyclerViewAdapter;
import com.example.smarthome.ui.Home.HomeRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

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
        Api.getInstance(this.getApplicationContext()).getStateLamp(lamp.getId(), response -> {
            stateLamp.setBrightness(response.getBrightness());
            stateLamp.setColor(response.getColor());
            stateLamp.setStatus(response.getStatus());
            loadScreen();
            lampBackTextView.setText("");
        }, error -> lampBackTextView.setText(R.string.device_no_connection));

        aSwitch.setOnClickListener(t -> {
            Api.getInstance(this.getApplicationContext()).lampSwitch(lamp.getId(), aSwitch.isChecked(), response -> {
                if (!response) {
                    aSwitch.toggle();
                } else {
                    aSwitch.setText((aSwitch.isChecked()) ? "on" : "off");
                }
            }, error -> {
                aSwitch.toggle();
                aSwitch.setText((aSwitch.isChecked()) ? "on" : "off");
                Toast.makeText(this, getApplicationContext().getResources().getString(R.string.smthWrong) , Toast.LENGTH_LONG).show();
            });
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int dimValue = seekBar.getProgress();
                Log.i("MyLog", "seekbar value: " + dimValue);
                Api.getInstance(getApplicationContext()).lampSetBrightness(lamp.getId(), dimValue, response -> {
                    if (response != dimValue) {
                        Toast.makeText(LampActivity.this,  getApplicationContext().getResources().getString(R.string.setBright) + dimValue, Toast.LENGTH_LONG).show();
                    } else
                        Toast.makeText(LampActivity.this, getApplicationContext().getResources().getString(R.string.noChangeBright), Toast.LENGTH_LONG).show();
                }, error -> {
                    Log.i("MyLog", error.toString());
                    Toast.makeText(LampActivity.this, getApplicationContext().getResources().getString(R.string.smthWrong), Toast.LENGTH_LONG).show();
                });
            }
        });

        white.setOnClickListener(t -> {
            Api.getInstance(this.getApplicationContext()).lampSetColor(lamp.getId(), "FFEB3B", response -> {
                if (response != null && !response.equals("FFEB3B")) {
                    Toast.makeText(LampActivity.this, getApplicationContext().getResources().getString(R.string.setWhite) , Toast.LENGTH_LONG).show();
                }
            }, error -> {
                Log.i("MyLog", error.toString());
                Toast.makeText(LampActivity.this, getApplicationContext().getResources().getString(R.string.smthWrong), Toast.LENGTH_LONG).show();
            });
        });
        yellow.setOnClickListener(t -> {
            Api.getInstance(this.getApplicationContext()).lampSetColor(lamp.getId(), "FFEB3B", response -> {
                if (response != null && !response.equals("FFEB3B")) {
                    Toast.makeText(LampActivity.this, getApplicationContext().getResources().getString(R.string.setYellow) , Toast.LENGTH_LONG).show();
                }
            }, error -> {
                Log.i("MyLog", error.toString());
                Toast.makeText(LampActivity.this, getApplicationContext().getResources().getString(R.string.smthWrong), Toast.LENGTH_LONG).show();
            });
        });
        red.setOnClickListener(t -> {
            Api.getInstance(this.getApplicationContext()).lampSetColor(lamp.getId(), "FF0000", response -> {
                if (response != null && !response.equals("FF0000")) {
                    Toast.makeText(LampActivity.this, getApplicationContext().getResources().getString(R.string.setRed), Toast.LENGTH_LONG).show();
                }
            }, error -> {
                Log.i("MyLog", error.toString());
                Toast.makeText(LampActivity.this, getApplicationContext().getResources().getString(R.string.smthWrong), Toast.LENGTH_LONG).show();
            });
        });
        green.setOnClickListener(t -> {
            Api.getInstance(this.getApplicationContext()).lampSetColor(lamp.getId(), "8BC34A", response -> {
                if (response != null && !response.equals("8BC34A")) {
                    Toast.makeText(LampActivity.this, getApplicationContext().getResources().getString(R.string.setGreen ), Toast.LENGTH_LONG).show();
                }
            }, error -> {
                Log.i("MyLog", error.toString());
                Toast.makeText(LampActivity.this, getApplicationContext().getResources().getString(R.string.smthWrong), Toast.LENGTH_LONG).show();
            });
        });
        blue.setOnClickListener(t -> {
            Api.getInstance(this.getApplicationContext()).lampSetColor(lamp.getId(), "3F51B5", response -> {
                if (response != null && !response.equals("3F51B5")) {
                    Toast.makeText(LampActivity.this, getApplicationContext().getResources().getString(R.string.setBlue), Toast.LENGTH_LONG).show();
                }
            }, error -> {
                Log.i("MyLog", error.toString());
                Toast.makeText(LampActivity.this, getApplicationContext().getResources().getString(R.string.smthWrong), Toast.LENGTH_LONG).show();
            });
        });
        pink.setOnClickListener(t -> {
            Api.getInstance(this.getApplicationContext()).lampSetColor(lamp.getId(), "FF2EE6", response -> {
                if (response != null && !response.equals("FF2EE6")) {
                    Toast.makeText(LampActivity.this, getApplicationContext().getResources().getString(R.string.setPink ) , Toast.LENGTH_LONG).show();
                }
            }, error -> {
                Log.i("MyLog", error.toString());
                Toast.makeText(LampActivity.this, getApplicationContext().getResources().getString(R.string.smthWrong), Toast.LENGTH_LONG).show();
            });
        });
        purple.setOnClickListener(t -> {
            Api.getInstance(this.getApplicationContext()).lampSetColor(lamp.getId(), "8F1AA3", response -> {
                if (response != null && !response.equals("8F1AA3")) {
                    Toast.makeText(LampActivity.this, getApplicationContext().getResources().getString(R.string.setpurple ), Toast.LENGTH_LONG).show();
                }
            }, error -> {
                Log.i("MyLog", error.toString());
                Toast.makeText(LampActivity.this, getApplicationContext().getResources().getString(R.string.smthWrong), Toast.LENGTH_LONG).show();
            });
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
