package com.example.smarthome.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.smarthome.API.APIcontrol;
import com.example.smarthome.R;
import com.example.smarthome.Room;
import com.example.smarthome.RoomMeta;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private Button addroomBtn;
    private Button getroomBtn;
    private TextView resultTextView;
    private Room room;
    private ArrayList<Room> rooms;
    private Context context;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        addroomBtn = root.findViewById(R.id.addroomBtn);
        getroomBtn = root.findViewById(R.id.getroomsBtn);
        resultTextView = root.findViewById(R.id.resultTextView);

        addroomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context = v.getContext();
                room = new Room("leniasRoom", new RoomMeta("9m2"));
                APIcontrol.getInstance(context).addRoom(room, new Response.Listener<Room>() {
                    @Override
                    public void onResponse(Room response) {
                        room.setId(response.getId());
                        resultTextView.setText("add Room " + room.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        resultTextView.setText("errrooorr");
                    }
                });
            }
        });

        getroomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context = v.getContext();
                APIcontrol.getInstance(context).getRoom(room.getId(), new Response.Listener<Room>() {
                    @Override
                    public void onResponse(Room response) {
                        resultTextView.setText("list Room: " + response.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        resultTextView.setText("EERROORRr");
                    }
                });
            }
        });
        return root;
    }
}