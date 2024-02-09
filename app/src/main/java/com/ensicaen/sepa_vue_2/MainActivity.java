package com.ensicaen.sepa_vue_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ensicaen.sepa_vue_2.data.RetrofitService;
import com.ensicaen.sepa_vue_2.data.SepaApi;
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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        setContentView(R.layout.activity_main);
    }

//    @Override
//    protected void onResume() {
//        RetrofitService retrofitService = new RetrofitService();
//        SepaApi sepaApi = retrofitService.getRetrofit().create(SepaApi.class);
//        String email ="miguel@gmail.com";
//        RequestBody body =
//                RequestBody.create(MediaType.parse("text/plain"), email);
//
//        Call<LoggedInUser> callAsync = sepaApi.getUserAccount(body);
//            callAsync.enqueue(new Callback<LoggedInUser>() {
//                @Override
//                public void onResponse(Call<LoggedInUser> call, Response<LoggedInUser> response) {
//                    Logger.getLogger(LoginActivity.class.getName()).log(Level.INFO, String.valueOf(response.body().toString()));
//                    Logger.getLogger(LoginActivity.class.getName()).log(Level.INFO,"Nom User "+response.body().getLastName());
//                    //user[0] = new LoggedInUser(response.body().getUserId(),response.body().getLastName(),response.body().getFirstName(),response.body().getIban(),response.body().getBic(),response.body().getAmount(),response.body().getCurrency());
//                    Logger.getLogger(LoginActivity.class.getName()).log(Level.INFO,"Connexion Ok");
//                }
//                @Override
//                public void onFailure(Call<LoggedInUser> call, Throwable t) {
//                    Logger.getLogger(LoginActivity.class.getName()).log(Level.SEVERE,t.toString());
//                }
//            });
//
////        RequestQueue requestQueue = Volley.newRequestQueue(this);
////        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://10.0.2.2/comptes/",
////            response -> {
////                Logger.getLogger(LoginActivity.class.getName()).log(Level.INFO, "OK");
////                Logger.getLogger(LoginActivity.class.getName()).log(Level.INFO, response);
////            },
////                error -> {
////                    Logger.getLogger(LoginActivity.class.getName()).log(Level.INFO, "KO");
////                    Logger.getLogger(LoginActivity.class.getName()).log(Level.SEVERE,error.toString());
////                });
////        requestQueue.add(stringRequest);
//
//        super.onResume();
//    }
}