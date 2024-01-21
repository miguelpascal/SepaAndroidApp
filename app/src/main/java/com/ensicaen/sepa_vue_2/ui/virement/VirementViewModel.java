package com.ensicaen.sepa_vue_2.ui.virement;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class VirementViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public VirementViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Effectuer un virement");
    }

    public LiveData<String> getText() {
        return mText;
    }
}