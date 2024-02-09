package com.ensicaen.sepa_vue_2.data;
import com.ensicaen.sepa_vue_2.ui.login.LoginActivity;
import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import okhttp3.OkHttpClient;
import retrofit2.converter.gson.GsonConverterFactory;

import retrofit2.Retrofit;

public class RetrofitService{
    private Retrofit retrofit;
    public RetrofitService() {
        Logger.getLogger(LoginActivity.class.getName()).log(Level.INFO,"Initialisation de retrofit");
        initializeRetrofit();
        Logger.getLogger(LoginActivity.class.getName()).log(Level.INFO,"Fin initialisation de retrofit");
    }

    private void initializeRetrofit() {
        Logger.getLogger(LoginActivity.class.getName()).log(Level.INFO,"Construction du base URl retrofit");
        try{
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.connectTimeout(0, TimeUnit.MINUTES) // connect timeout
                    .writeTimeout(0, TimeUnit.MINUTES) // write timeout
                    .readTimeout(0, TimeUnit.MINUTES); // read timeout

            retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2/")
                    //.baseUrl("http://serveur-apprentissage.ensicaen.fr:8080/library/")
                    //.baseUrl("http://10.214.11.76/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();

        } catch (Exception e){
            Logger.getLogger(LoginActivity.class.getName()).log(Level.INFO,retrofit + e.toString());
            throw e;
        }
        Logger.getLogger(LoginActivity.class.getName()).log(Level.INFO,"Fin de construction du base URl retrofit");
    }
    public Retrofit getRetrofit() {
        return retrofit;
    }
}
