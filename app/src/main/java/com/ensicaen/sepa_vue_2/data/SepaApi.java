package com.ensicaen.sepa_vue_2.data;

import com.ensicaen.sepa_vue_2.data.model.LoggedInUser;


import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface SepaApi {


    @POST("comptes/singIn")
    Call<LoggedInUser>getUserAccount(@Body RequestBody email);
}
