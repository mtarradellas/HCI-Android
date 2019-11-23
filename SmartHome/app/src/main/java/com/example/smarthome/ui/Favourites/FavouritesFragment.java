package com.example.smarthome.ui.Favourites;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.smarthome.API.Api;
import com.example.smarthome.Device;
import com.example.smarthome.DeviceMeta;
import com.example.smarthome.R;
import com.example.smarthome.Room;
import com.example.smarthome.Type;
import com.example.smarthome.ui.Home.HomeItem;
import com.example.smarthome.ui.Home.HomeRecyclerViewAdapter;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavouritesFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    private ArrayList<FavouritesItem> favouritesItems;
    private TextView favouritesBackTextView;

    public FavouritesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_favourites, container, false);
        recyclerView = v.findViewById(R.id.favouritesRecyclerView);
        FavouritesRecyclerViewAdapter recyclerViewAdapter = new FavouritesRecyclerViewAdapter(getContext(), favouritesItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerViewAdapter);
        favouritesBackTextView = v.findViewById(R.id.favouritesBackTextView);

        swipeRefreshLayout = v.findViewById(R.id.favouritesSwipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(this);

        refreshFavourites();

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        favouritesItems = new ArrayList<>();
        refreshFavourites();
    }

    private void refreshFavourites() {
        Api.getInstance(getContext()).getDevices(new Response.Listener<ArrayList<Device>>() {
            @Override
            public void onResponse(ArrayList<Device> response) {
                if (response.size() == 0) {
                    favouritesBackTextView.setText(R.string.favourites_list_empty);
                } else {
                    favouritesItems.clear();
                    String name;
                    Type type;
                    DeviceMeta meta;
                    String id;
                    for (Device device : response) {
                        id = device.getId();
                        name = device.getName();
                        type = device.getTypeId();
                        meta = device.getMeta();
                        if (device.isFav()) {
                            favouritesItems.add(new FavouritesItem(new Device(id, name, type, meta), R.drawable.ic_lightbulb_outline_black_24dp));
                        }
                    }
                    if (favouritesItems.size() == 0) {
                        favouritesBackTextView.setText(R.string.favourites_list_empty);
                    } else {
                    favouritesBackTextView.setText("");
                    }
                }
                FavouritesRecyclerViewAdapter recyclerViewAdapter = new FavouritesRecyclerViewAdapter(getContext(), favouritesItems);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView.setAdapter(recyclerViewAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                favouritesItems.clear();
                favouritesBackTextView.setText(R.string.favourites_no_connection);
            }
        });
    }

    public void setFavouritesBackText(String text) {
        favouritesBackTextView.setText(text);
    }

    @Override
    public void onRefresh() {
        refreshFavourites();
        swipeRefreshLayout.setRefreshing(false);
    }

}
