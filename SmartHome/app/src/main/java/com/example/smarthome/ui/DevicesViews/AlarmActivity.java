package com.example.smarthome.ui.DevicesViews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smarthome.AboutUsActivity;
import com.example.smarthome.Device;
import com.example.smarthome.MainActivity;
import com.example.smarthome.R;
import com.example.smarthome.SettingsActivity;

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
