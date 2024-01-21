package com.ensicaen.sepa_vue_2.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("FR76 3000 1140 0000 0010 0023 128");
    }

    public LiveData<String> getText() {
        return mText;
    }
}