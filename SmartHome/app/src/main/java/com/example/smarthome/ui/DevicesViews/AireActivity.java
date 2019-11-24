package com.example.smarthome.ui.DevicesViews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.smarthome.Device;
import com.example.smarthome.MainActivity;
import com.example.smarthome.R;

public class AireActivity extends AppCompatActivity {

    private Device ac;
    private TextView acBackTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aire);

        Intent intent = getIntent();
        ac = intent.getParcelableExtra(MainActivity.EXTRA_DEVICE);

        acBackTextView = findViewById(R.id.acBackTextView);
        acBackTextView.setText("");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(ac.getName());
    }
}
