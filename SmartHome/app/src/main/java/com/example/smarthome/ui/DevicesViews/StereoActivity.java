package com.example.smarthome.ui.DevicesViews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.smarthome.AboutUsActivity;

import com.example.smarthome.API.Api;

import com.example.smarthome.Device;
import com.example.smarthome.DeviceStates.StateStereo;
import com.example.smarthome.MainActivity;
import com.example.smarthome.R;
import com.example.smarthome.SettingsActivity;

public class StereoActivity extends AppCompatActivity {

    private Device stereo;
    private TextView stereoBackTextView;

    private ToggleButton toggleOn;
    private ToggleButton togglePlay;
    private SeekBar volumen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stereo);

        Intent intent = getIntent();
        stereo = intent.getParcelableExtra(MainActivity.EXTRA_DEVICE);

        stereoBackTextView = findViewById(R.id.stereoBackTextView);
        stereoBackTextView.setText("");

        togglePlay = findViewById(R.id.stereoPlayToggle);
        volumen = findViewById(R.id.stereoDimmerSeekBar);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(stereo.getName());

        StateStereo stateStereo = new StateStereo();
        Api.getInstance(this.getApplicationContext()).getStateStereo(stereo.getId(), response -> {
            stateStereo.setVolume(response.getVolume());
            stateStereo.setStatus(response.getStatus());
            togglePlay.setChecked((stateStereo.getStatus().contains("pau") || stateStereo.getStatus().contains("stop")));
            volumen.setProgress(stateStereo.getVolume() * 10);
            stereoBackTextView.setText("");
        }, error -> {
            stereoBackTextView.setText(R.string.device_no_connection);
        });

        togglePlay.setOnClickListener( t -> {
            Api.getInstance(this.getApplicationContext()).stereoSwitch(stereo.getId(), !togglePlay.isChecked(), response -> {
                if (!response) {
                    togglePlay.toggle();
                }
            }, error -> {
                togglePlay.toggle();
                Toast.makeText(this, "Something went wrong when modifying this speaker", Toast.LENGTH_LONG).show();
            });
        });
        volumen.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int volume = (volumen.getProgress()/10);
                Api.getInstance(getApplicationContext()).stereoChangeVolume(stereo.getId(), volume, response -> {
                    if (response != volume) {
                        Toast.makeText(StereoActivity.this, "Set volume to: " + volume, Toast.LENGTH_LONG).show();
                    } else
                        Toast.makeText(StereoActivity.this, "Didn't change volume", Toast.LENGTH_LONG).show();
                }, error -> {
                    Log.i("MyLog", error.toString());
                    Toast.makeText(StereoActivity.this, "Something went wrong ", Toast.LENGTH_LONG).show();
                });
            }
        });
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
}
