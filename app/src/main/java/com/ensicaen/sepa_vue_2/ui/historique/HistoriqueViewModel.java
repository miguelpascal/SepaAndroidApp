package com.ensicaen.sepa_vue_2.ui.historique;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ensicaen.sepa_vue_2.SepaActivity;
import com.ensicaen.sepa_vue_2.service.RetrofitService;
import com.ensicaen.sepa_vue_2.api.SepaApi;
import com.ensicaen.sepa_vue_2.data.model.HistoriqueModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HistoriqueViewModel extends ViewModel {


    private final MutableLiveData<String> mText;
    final MutableLiveData<List<HistoriqueModel>> historiqueVirements;


    public HistoriqueViewModel() {
        historiqueVirements = new MutableLiveData<>();
        mText = new MutableLiveData<>();
        mText.setValue("Historique des virements");
    }

    public LiveData<List<HistoriqueModel>> getHistoriqueVirements() {
        // Appel de la méthode pour charger l'historique
        return historiqueVirements;
    }

    public void chargerHistoriqueVirements(long user_ID) {
        // Simulation de l'obtention de l'historique des virements depuis une source de données
        List<HistoriqueModel> historiques = new ArrayList<>();
        // Ajoutez plus d'objets Virement selon vos besoins
        RetrofitService retrofitService = new RetrofitService();
        SepaApi sepaApi = retrofitService.getRetrofit().create(SepaApi.class);
        Call<List<HistoriqueModel>> callAsync = sepaApi.getHistoriqueUser(user_ID);
        callAsync.enqueue(new Callback<List<HistoriqueModel>>() {
            @Override
            public void onResponse(Call<List<HistoriqueModel>> call, Response<List<HistoriqueModel>> response) {
                if (response.body().size()!=0){
                    for (HistoriqueModel hist:response.body()) {
                        historiques.add(new HistoriqueModel(hist.getTransactionDate(), hist.getMotif(), hist.getAmount(), hist.getMode(), hist.getDestinataire()));
                        Logger.getLogger(SepaActivity.class.getName()).log(Level.INFO, hist.toString());
                    }

                } else Logger.getLogger(SepaActivity.class.getName()).log(Level.INFO,"No credit historic for this user");
            }
            @Override
            public void onFailure(Call<List<HistoriqueModel>> call, Throwable t) {
                Logger.getLogger(SepaActivity.class.getName()).log(Level.SEVERE,"failed to fetch data");
                Logger.getLogger(SepaActivity.class.getName()).log(Level.SEVERE,t.toString());
            }
        });
        // Mettez à jour le LiveData avec l'historique obtenu
        historiqueVirements.setValue(historiques);
    }


    public LiveData<String> getText() {
        return mText;
    }
}
