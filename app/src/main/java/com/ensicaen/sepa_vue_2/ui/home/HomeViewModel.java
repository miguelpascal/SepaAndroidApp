package com.ensicaen.sepa_vue_2.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ensicaen.sepa_vue_2.data.model.LoggedInUserModel;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<LoggedInUserModel> homeData = new MutableLiveData<LoggedInUserModel>();

    public void setHomeData(LoggedInUserModel user) {
        homeData.setValue(user);
    }

    public LiveData<LoggedInUserModel> getLoggedInUser() {
        return homeData;
    }
}