package com.ensicaen.sepa_vue_2.data;

import android.widget.Toast;

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

        try {
            // TODO: handle loggedInUser authentication
            Logger.getLogger(LoginActivity.class.getName()).log(Level.INFO,"Demarrage de la requête Api pour le login 0");
            RetrofitService retrofitService = new RetrofitService();
            Logger.getLogger(LoginActivity.class.getName()).log(Level.INFO,"Demarrage de la requête Api pour le login 1");
            final LoggedInUser[] user = {new LoggedInUser()};
            Logger.getLogger(LoginActivity.class.getName()).log(Level.INFO,"Demarrage de la requête Api pour le login 2");
            SepaApi sepaApi = retrofitService.getRetrofit().create(SepaApi.class);
            Logger.getLogger(LoginActivity.class.getName()).log(Level.INFO,"Demarrage de la requête Api pour le login 3");
            Logger.getLogger(LoginActivity.class.getName()).log(Level.INFO,username +" Fin Demarrage de la requête Api pour le login 3");
            RequestBody body =
                    RequestBody.create(MediaType.parse("text/plain"), username);
            Call<LoggedInUser> callAsync = sepaApi.getUserAccount(body);
            Logger.getLogger(LoginActivity.class.getName()).log(Level.INFO,"Fin Demarrage de la requête Api pour le login 3");
            try {
                callAsync.enqueue(new Callback<LoggedInUser>() {
                            @Override
                            public void onResponse(Call<LoggedInUser> call, Response<LoggedInUser> response) {
                                Logger.getLogger(LoginActivity.class.getName()).log(Level.INFO,"Nom User "+response.body().getLastName());
                                user[0] = new LoggedInUser(response.body().getUserId(),response.body().getLastName(),response.body().getFirstName(),response.body().getIban(),response.body().getBic(),response.body().getAmount(),response.body().getCurrency());
                                Logger.getLogger(LoginActivity.class.getName()).log(Level.INFO,"Connexion Ok");
                            }
                            @Override
                            public void onFailure(Call<LoggedInUser> call, Throwable t) {
                                Logger.getLogger(LoginActivity.class.getName()).log(Level.SEVERE,t.toString());
                            }
                });
            } catch (RuntimeException ex) {
                Logger.getLogger(LoginActivity.class.getName()).log(Level.SEVERE, ex.toString());
            }
            // TODO: handle loggedInUser Done
            return new Result.Success<>(user[0]);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }

    }

    public void logout() {
        // TODO: revoke authentication
    }
}