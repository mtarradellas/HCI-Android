package com.example.smarthome.ui.routines;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RoutinesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public RoutinesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Routines");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
