package com.example.smarthome.ui.DevicesViews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.smarthome.Device;
import com.example.smarthome.MainActivity;
import com.example.smarthome.R;

public class AlarmActivity extends AppCompatActivity {

    private Device alarm;
    private TextView alarmBackTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        Intent intent = getIntent();
        alarm = intent.getParcelableExtra(MainActivity.EXTRA_DEVICE);

        alarmBackTextView = findViewById(R.id.alarmBackTextView);
        alarmBackTextView.setText("");


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(alarm.getName());
    }
}
