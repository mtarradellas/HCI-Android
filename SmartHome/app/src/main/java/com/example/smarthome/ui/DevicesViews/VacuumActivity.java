package com.example.smarthome.ui.DevicesViews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.smarthome.Device;
import com.example.smarthome.MainActivity;
import com.example.smarthome.R;

public class VacuumActivity extends AppCompatActivity {

    private Device vacuum;
    private TextView vacuumBackTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacuum);

        Intent intent = getIntent();
        vacuum = intent.getParcelableExtra(MainActivity.EXTRA_DEVICE);

        vacuumBackTextView = findViewById(R.id.doorBackTextView);
        vacuumBackTextView.setText("");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(vacuum.getName());
    }
}
