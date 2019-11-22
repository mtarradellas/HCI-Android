package com.example.smarthome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.smarthome.ui.Home.HomeRecyclerViewAdapter;

public class RoomViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_view);

        Intent intent = getIntent();
        Room room = intent.getParcelableExtra(HomeRecyclerViewAdapter.EXTRA_ROOM);

        TextView textView = findViewById(R.id.roomViewTextView);
        textView.setText(room.getName());
    }
}
