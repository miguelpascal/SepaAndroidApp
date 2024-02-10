package com.ensicaen.sepa_vue_2.data;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.ensicaen.sepa_vue_2.AccueilActivity;
import com.ensicaen.sepa_vue_2.MainActivity;
import com.ensicaen.sepa_vue_2.data.model.LoggedInUser;
import com.ensicaen.sepa_vue_2.ui.login.LoginActivity;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(String username, String password) {
        LoggedInUser []finalUser = {new LoggedInUser()};

        try {
            // TODO: handle loggedInUser authentication

        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }

        return new Result.Success<>(finalUser[0]);
    }

    public void logout() {
        // TODO: revoke authentication
    }
}