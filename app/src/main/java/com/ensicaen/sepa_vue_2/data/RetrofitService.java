package com.ensicaen.sepa_vue_2.data;
import com.ensicaen.sepa_vue_2.ui.login.LoginActivity;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import okhttp3.OkHttpClient;
import retrofit2.converter.gson.GsonConverterFactory;

import retrofit2.Retrofit;

public class RetrofitService{
    private Retrofit retrofit;
    public RetrofitService() {
        initializeRetrofit();
    }

    private void initializeRetrofit() {
        try{
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.connectTimeout(0, TimeUnit.MINUTES) // connect timeout
                    .writeTimeout(0, TimeUnit.MINUTES) // write timeout
                    .readTimeout(0, TimeUnit.MINUTES); // read timeout

            retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.245.146.109/")
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
