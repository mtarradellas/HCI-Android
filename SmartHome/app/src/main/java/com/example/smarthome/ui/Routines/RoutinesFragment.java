package com.example.smarthome.ui.Routines;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.smarthome.API.Api;
import com.example.smarthome.R;
import com.example.smarthome.Room;
import com.example.smarthome.Routine;
import com.example.smarthome.ui.Home.HomeItem;
import com.example.smarthome.ui.Home.HomeRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class RoutinesFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    private ArrayList<RoutinesItem> routinesItems;
    private TextView routinesBackTextView;

    public RoutinesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_routines, container, false);
        recyclerView = v.findViewById(R.id.routinesRecyclerView);
        RoutinesRecyclerViewAdapter recyclerViewAdapter = new RoutinesRecyclerViewAdapter(getContext(), routinesItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerViewAdapter);
        routinesBackTextView = v.findViewById(R.id.routinesBackTextView);

        swipeRefreshLayout = v.findViewById(R.id.routinesSwipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(this);

        refreshRoutines();

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        routinesItems = new ArrayList<>();
        refreshRoutines();
    }

    private void refreshRoutines() {
        Api.getInstance(getContext()).getRoutines(new Response.Listener<ArrayList<Routine>>() {
            @Override
            public void onResponse(ArrayList<Routine> response) {
                if (response.size() == 0) {
                    routinesBackTextView.setText(R.string.routines_list_empty);
                } else {
                    routinesItems.clear();
                    String name;
                    String id;
                    for (Routine routine : response) {
                        name = routine.getName();
                        id = routine.getId();
                        routinesItems.add(new RoutinesItem(new Routine(id, name, null, null), R.drawable.ic_alarm_black_24dp));
                    }
                    routinesBackTextView.setText("");
                }
                RoutinesRecyclerViewAdapter recyclerViewAdapter = new RoutinesRecyclerViewAdapter(getContext(), routinesItems);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView.setAdapter(recyclerViewAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                routinesItems.clear();
                routinesBackTextView.setText(R.string.routines_no_connection);
            }
        });
    }

    public void setRoutinesBackTextView(String text) {
        routinesBackTextView.setText(text);
    }

    @Override
    public void onRefresh() {
        refreshRoutines();
        swipeRefreshLayout.setRefreshing(false);
    }

}
