package com.example.smarthome.ui.DevicesViews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import com.example.smarthome.Device;
import com.example.smarthome.MainActivity;
import com.example.smarthome.R;

public class StereoActivity extends AppCompatActivity {

    private Device stereo;
    private TextView stereoBackTextView;
    private Switch aSwitch;
    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stereo);

        Intent intent = getIntent();
        stereo = intent.getParcelableExtra(MainActivity.EXTRA_DEVICE);

        stereoBackTextView = findViewById(R.id.doorBackTextView);
        stereoBackTextView.setText("");

        aSwitch = findViewById(R.id.lampSwitch);
        seekBar = findViewById(R.id.lampDimmerSeekBar);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(stereo.getName());
    }
}
