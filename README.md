# Sepa android client


## Description

Ce projet est conçu dans le but de proposer un client Android permettant de se connecter à un serveur. Le serveur permet de réaliser les virements instantanées SEPA.

## Configuration de l'adresse serveur

Dans cette classe retrofit, vous pouvez configurer l'adresse de votre serveur afin qu'il puisse être requêté par notre client android.

```java
package com.ensicaen.sepa_vue_2.service;

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
                    .baseUrl("http://192.168.137.1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();

        } catch (Exception e){
            Logger.getLogger(RetrofitService.class.getName()).log(Level.INFO,retrofit + e.toString());
            throw e;
        }
        Logger.getLogger(RetrofitService.class.getName()).log(Level.INFO,"Fin de construction du base URl retrofit");
    }
    public Retrofit getRetrofit() {
        return retrofit;
    }
}

```

## Configuration des routes vers les API du serveur

Dans cette classe, vous pouvez ajouter ou retirer des routes ver le serveur sepa que nous avons conçu.
```java
package com.ensicaen.sepa_vue_2.api;

import com.ensicaen.sepa_vue_2.data.model.LoggedInUserModel;
import com.ensicaen.sepa_vue_2.data.model.HistoriqueModel;
import com.ensicaen.sepa_vue_2.data.model.PhoneCredit;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface SepaApi {


    @POST("comptes/signIn")
    Call<LoggedInUserModel>getUserAccount(@Body RequestBody email);

    @GET("comptes/user/home/{userId}")
    Call<LoggedInUserModel>getUserAccountByID(@Path("userId") long userId);

    @GET("comptes/credit/history/{userId}")
    Call <List<HistoriqueModel>> getHistoriqueUser(@Path("userId") long userId);

    @POST("comptes/phone/credit")
    Call<Void> creditAccountWithNumber(@Body PhoneCredit phoneCreditDTO);
}


```
## Name
Sepa client


## Visuals



## Installation
le fichier d'installation apk se trouve dans le répertoire
#### app/build/outputs/apk/debug ####


## Support
Tell people where they can go to for help. It can be any combination of an issue tracker, a chat room, an email address, etc.

## Authors and acknowledgment
Pascal KAMDEM, Pamela Monthe

## License
For open source projects, say how it is licensed.

## Project status
Projet en cours développement mais avance très lentement, besoin de contributeurs.
