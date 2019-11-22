package com.example.smarthome.ui.Home;


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

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private HomeFragmentListener listener;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    private ArrayList<HomeItem> homeItems;
    private TextView homeBackTextView;

    public interface HomeFragmentListener {

    }

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = v.findViewById(R.id.homeRecyclerView);
        HomeRecyclerViewAdapter recyclerViewAdapter = new HomeRecyclerViewAdapter(getContext(), homeItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerViewAdapter);
        homeBackTextView = v.findViewById(R.id.homeBackTextView);

        swipeRefreshLayout = v.findViewById(R.id.homeSwipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(this);

        refreshRooms();

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        homeItems = new ArrayList<>();
        refreshRooms();
    }

    private void refreshRooms() {
        Api.getInstance(getContext()).getRooms(new Response.Listener<ArrayList<Room>>() {
            @Override
            public void onResponse(ArrayList<Room> response) {
                homeItems.clear();
                String name;
                for (Room room : response) {
                    name = room.getName();
                    homeItems.add(new HomeItem(new Room(name), R.drawable.ic_local_hotel_black_24dp));
                }
                homeBackTextView.setText("");
                HomeRecyclerViewAdapter recyclerViewAdapter = new HomeRecyclerViewAdapter(getContext(), homeItems);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView.setAdapter(recyclerViewAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
    }

    @Override
    public void onRefresh() {
        refreshRooms();
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof HomeFragmentListener) {
            listener = (HomeFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement HomeFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

}
