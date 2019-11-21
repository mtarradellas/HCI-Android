package com.example.smarthome.ui.Home;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smarthome.R;
import com.example.smarthome.Room;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private HomeFragmentListener listener;
    private ArrayList<Room> roomArrayList;
    private RecyclerView recyclerView;
    private List<HomeItem> list;

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
        recyclerView = (RecyclerView) v.findViewById(R.id.homeRecyclerView);
        HomeRecyclerViewAdapter recyclerViewAdapter = new HomeRecyclerViewAdapter(getContext(), list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerViewAdapter);



        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        list = new ArrayList<>();
        list.add(new HomeItem(R.drawable.ic_child_care_black_24dp, "Baby", "feliz"));
        list.add(new HomeItem(R.drawable.ic_sentiment_dissatisfied_black_24dp, "Teen", "quiere morir"));
        list.add(new HomeItem(R.drawable.ic_sentiment_very_dissatisfied_black_24dp, "teen", "al fin se mato"));

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
