package com.example.smarthome.ui.RoomView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.smarthome.API.Api;
import com.example.smarthome.Device;
import com.example.smarthome.DeviceMeta;
import com.example.smarthome.R;
import com.example.smarthome.Room;
import com.example.smarthome.Type;
import com.example.smarthome.ui.Favourites.FavouritesItem;
import com.example.smarthome.ui.Favourites.FavouritesRecyclerViewAdapter;
import com.example.smarthome.ui.Home.HomeRecyclerViewAdapter;

import java.util.ArrayList;

public class RoomViewActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    private ArrayList<RoomItem> roomItems;
    private TextView roomBackTextView;

    private Room room;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_view);

        Intent intent = getIntent();
        room = intent.getParcelableExtra(HomeRecyclerViewAdapter.EXTRA_ROOM);
        roomItems = new ArrayList<>();
        roomBackTextView = findViewById(R.id.roomBackTextView);

        roomBackTextView.setText(room.getName());

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(room.getName());

        recyclerView = findViewById(R.id.roomRecyclerView);
        RoomRecyclerViewAdapter recyclerViewAdapter = new RoomRecyclerViewAdapter(this.getApplicationContext(), roomItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerViewAdapter);

        swipeRefreshLayout = findViewById(R.id.roomSwipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(this);

        refreshRoom();

    }

    private void refreshRoom() {
        Api.getInstance(this.getApplicationContext()).getRoomDevices(room.getId(), new Response.Listener<ArrayList<Device>>() {
            @Override
            public void onResponse(ArrayList<Device> response) {
                if (response.size() == 0) {
                    roomBackTextView.setText(R.string.room_devices_list_empty);
                } else {
                    roomItems.clear();
                    String name;
                    Type type;
                    DeviceMeta meta;
                    String id;
                    for (Device device : response) {
                        name = device.getName();
                        type = device.getTypeId();
                        meta = device.getMeta();
                        id = device.getId();
                        roomItems.add(new RoomItem(new Device(id, name, type, meta)));
                        }
                    if (roomItems.size() == 0) {
                        roomBackTextView.setText(R.string.room_devices_list_empty);
                    } else {
                        roomBackTextView.setText("");
                    }
                }
                RoomRecyclerViewAdapter recyclerViewAdapter = new RoomRecyclerViewAdapter(getApplicationContext(), roomItems);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setAdapter(recyclerViewAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                roomItems.clear();
                roomBackTextView.setText(R.string.room_no_connection);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit_room_menu, menu);
        return true;
    }

    @Override
    public void onRefresh() {
        //refreshRoom();
        swipeRefreshLayout.setRefreshing(false);
    }
}
