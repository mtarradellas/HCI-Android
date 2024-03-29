package com.example.smarthome.ui.DevicesViews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.smarthome.API.Api;
import com.example.smarthome.AboutUsActivity;
import com.example.smarthome.Device;
import com.example.smarthome.DeviceStates.StateBlinds;
import com.example.smarthome.MainActivity;
import com.example.smarthome.R;
import com.example.smarthome.SettingsActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class BlindActivity extends AppCompatActivity {

    private Device blind;
    private TextView blindBackTextView;
    private ToggleButton toogle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blind);

        Intent intent = getIntent();
        blind = intent.getParcelableExtra(MainActivity.EXTRA_DEVICE);

        blindBackTextView = findViewById(R.id.blindBacktextView);
        blindBackTextView.setText("");

        toogle = findViewById(R.id.blindToggle);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(blind.getName());
        StateBlinds stateBlinds = new StateBlinds();
        Api.getInstance(this.getApplicationContext()).getStateBlinds(blind.getId(), response -> {
            stateBlinds.setStatus(response.getStatus());
            toogle.setChecked(!stateBlinds.getStatus().contains("clos"));
            blindBackTextView.setText("");
        }, error -> blindBackTextView.setText(R.string.device_no_connection));
        toogle.setOnClickListener(t -> {
            Api.getInstance(this.getApplicationContext()).blindSwitch(blind.getId(), toogle.isChecked(), response -> {
                Log.i("MyLog", response.toString());
                if (!response) {
                    toogle.toggle();
                }

            }, error -> {
                toogle.toggle();
                Toast.makeText(this,getApplicationContext().getResources().getString(R.string.smthWrong), Toast.LENGTH_LONG).show();
            });
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
