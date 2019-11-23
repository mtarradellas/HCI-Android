package com.example.smarthome.ui.DevicesViews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.smarthome.Device;
import com.example.smarthome.R;
import com.example.smarthome.ui.Favourites.FavouritesRecyclerViewAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class BlindActivity extends AppCompatActivity {

    private Device blind;
    private TextView blindBackTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blind);

        Intent intent = getIntent();
        blind = intent.getParcelableExtra(FavouritesRecyclerViewAdapter.EXTRA_DEVICE);

        blindBackTextView = findViewById(R.id.blindBacktextView);
        blindBackTextView.setText("");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(blind.getName());
    }
}
