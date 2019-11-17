package com.example.smarthome.ui;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.smarthome.R;

import java.io.Console;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private HomeFragmentListener listener;
    private Button addBtn;
    private Button getBtn;
    private TextView resultTextView;

    public interface HomeFragmentListener {
        void onHomeAddClick(String string);
        void onHomeGetClick(String string);
    }

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        addBtn = v.findViewById(R.id.addBtn);
        getBtn = v.findViewById(R.id.getBtn);
        resultTextView = v.findViewById(R.id.resultTextView);
        resultTextView.setText("HI");

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onHomeAddClick("ADD ROOM");
                //resultTextView.setText("KILL");
            }
        });

        getBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onHomeGetClick("GET ROOM");
                //resultTextView.setText("DIE");
            }
        });

        return v;
    }

    public void updateTextView(String string) {
        resultTextView.setText(string);
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
