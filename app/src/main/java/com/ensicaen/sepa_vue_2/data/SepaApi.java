package com.ensicaen.sepa_vue_2.data;

import com.ensicaen.sepa_vue_2.data.model.LoggedInUserModel;
import com.ensicaen.sepa_vue_2.data.model.HistoriqueModel;
import com.ensicaen.sepa_vue_2.data.model.PhoneCredit;

import java.util.ArrayList;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface SepaApi {


    @POST("comptes/singIn")
    Call<LoggedInUserModel>getUserAccount(@Body RequestBody email);

    @GET("comptes/credit/history/{userId}")
    Call <ArrayList<HistoriqueModel>> getHistoriqueUser(@Path("userId") Long userId);

    @POST("comptes/phone/credit")
    Call creditAccountWithNumber(@Body PhoneCredit phoneCreditDTO);
}
