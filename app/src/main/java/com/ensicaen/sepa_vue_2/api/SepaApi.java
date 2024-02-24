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


    @POST("comptes/singIn")
    Call<LoggedInUserModel>getUserAccount(@Body RequestBody email);

    @GET("comptes/user/home/{userId}")
    Call<LoggedInUserModel>getUserAccountByID(@Path("userId") long userId);

    @GET("comptes/credit/history/{userId}")
    Call <List<HistoriqueModel>> getHistoriqueUser(@Path("userId") long userId);

    @POST("comptes/phone/credit")
    Call<Void> creditAccountWithNumber(@Body PhoneCredit phoneCreditDTO);
}
