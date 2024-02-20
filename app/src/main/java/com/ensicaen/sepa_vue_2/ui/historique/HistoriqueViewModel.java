package com.ensicaen.sepa_vue_2.ui.historique;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ensicaen.sepa_vue_2.AccueilActivity;
import com.ensicaen.sepa_vue_2.data.RetrofitService;
import com.ensicaen.sepa_vue_2.data.SepaApi;
import com.ensicaen.sepa_vue_2.data.model.HistoriqueModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HistoriqueViewModel extends ViewModel {


    private final MutableLiveData<String> mText;
    final MutableLiveData<ArrayList<HistoriqueModel>> historiqueVirements;


    public HistoriqueViewModel() {
        historiqueVirements = new MutableLiveData<>();
        mText = new MutableLiveData<>();
        mText.setValue("Historique des virements");
        chargerHistoriqueVirements();
    }

    public LiveData<ArrayList<HistoriqueModel>> getHistoriqueVirements() {
        // Appel de la méthode pour charger l'historique
        return historiqueVirements;
    }

    private void chargerHistoriqueVirements() {
        // Simulation de l'obtention de l'historique des virements depuis une source de données
        ArrayList<HistoriqueModel> historique = new ArrayList<>();
        // Ajoutez plus d'objets Virement selon vos besoins
        RetrofitService retrofitService = new RetrofitService();
        SepaApi sepaApi = retrofitService.getRetrofit().create(SepaApi.class);
        long user_ID = 2;
        Call<ArrayList<HistoriqueModel>> callAsync = sepaApi.getHistoriqueUser(user_ID);
        callAsync.enqueue(new Callback<ArrayList<HistoriqueModel>>() {
            @Override
            public void onResponse(Call<ArrayList<HistoriqueModel>> call, Response<ArrayList<HistoriqueModel>> response) {
                if (response.body().size()!=0){
                    for (HistoriqueModel hist:response.body()) {
                        historique.add(new HistoriqueModel(hist.getTransactionDate(), hist.getMotif(), hist.getAmount(), hist.getMode(), hist.getDestinataire()));
                        Logger.getLogger(AccueilActivity.class.getName()).log(Level.INFO, hist.toString());
                    }

                } else Logger.getLogger(AccueilActivity.class.getName()).log(Level.INFO,"No credit historic for this user");
            }
            @Override
            public void onFailure(Call<ArrayList<HistoriqueModel>> call, Throwable t) {
                Logger.getLogger(AccueilActivity.class.getName()).log(Level.SEVERE,"failed to fetch data");
                Logger.getLogger(AccueilActivity.class.getName()).log(Level.SEVERE,t.toString());
            }
        });
        // Mettez à jour le LiveData avec l'historique obtenu
        historiqueVirements.setValue(historique);
    }


    public LiveData<String> getText() {
        return mText;
    }
}
