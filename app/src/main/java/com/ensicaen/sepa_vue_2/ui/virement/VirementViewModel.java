package com.ensicaen.sepa_vue_2.ui.virement;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class VirementViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public VirementViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is virement fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}