package com.example.smarthome.ui.DevicesViews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.smarthome.API.Api;
import com.example.smarthome.Device;
import com.example.smarthome.DeviceStates.StateBlinds;
import com.example.smarthome.MainActivity;
import com.example.smarthome.R;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
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
                Toast.makeText(this, "Something went wrong when modifying this blind", Toast.LENGTH_LONG).show();
            });
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }
}
