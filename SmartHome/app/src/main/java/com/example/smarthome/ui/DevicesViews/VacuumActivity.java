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
import android.widget.ToggleButton;

import com.example.smarthome.AboutUsActivity;
import com.example.smarthome.Device;
import com.example.smarthome.MainActivity;
import com.example.smarthome.R;
import com.example.smarthome.SettingsActivity;

public class VacuumActivity extends AppCompatActivity {

    private Device vacuum;
    private TextView vacuumBackTextView;
    private ToggleButton onToggle;
    private ToggleButton playToggle;
    private ToggleButton modeToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacuum);

        Intent intent = getIntent();
        vacuum = intent.getParcelableExtra(MainActivity.EXTRA_DEVICE);

        vacuumBackTextView = findViewById(R.id.vacuumBackTextView);
        vacuumBackTextView.setText("");

        onToggle = findViewById(R.id.vacuumOnToggle);
        playToggle = findViewById(R.id.vacuumPlayToggle);
        modeToggle = findViewById(R.id.vacuumModeToggle);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(vacuum.getName());
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
