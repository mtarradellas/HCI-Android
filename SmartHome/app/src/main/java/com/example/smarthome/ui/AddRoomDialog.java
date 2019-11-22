package com.example.smarthome.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.smarthome.R;
import com.google.android.material.textfield.TextInputEditText;

public class AddRoomDialog extends AppCompatDialogFragment {

    private TextInputEditText inputEditText;
    private AddRoomListener addRoomListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_room, null);

        inputEditText = view.findViewById(R.id.add_room_input);

        builder.setView(view)
                .setTitle("Add Room")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = inputEditText.getText().toString();
                        addRoomListener.applyAddRoom(name);

                    }
                });
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            addRoomListener = (AddRoomListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement AddRoomListener");
        }

    }

    public interface AddRoomListener {
        void applyAddRoom(String name);
    }
}
