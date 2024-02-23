package com.ensicaen.sepa_vue_2.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ensicaen.sepa_vue_2.AccueilActivity;
import com.ensicaen.sepa_vue_2.data.RetrofitService;
import com.ensicaen.sepa_vue_2.data.SepaApi;
import com.ensicaen.sepa_vue_2.data.model.HistoriqueModel;
import com.ensicaen.sepa_vue_2.data.model.LoggedInUserModel;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SepaViewModel extends ViewModel {

    private final MutableLiveData<LoggedInUserModel> loggedInUser = new MutableLiveData<LoggedInUserModel>();
    final MutableLiveData<List<HistoriqueModel>> historiqueVirements = new MutableLiveData<List<HistoriqueModel>>();

    public void setLoggedInUser(LoggedInUserModel user) {
        loggedInUser.setValue(user);
    }

    public void setListHisto(List<HistoriqueModel> historiqueModel){
        historiqueVirements.setValue(historiqueModel);
    }

    public LiveData<List<HistoriqueModel>> getHistoriqueVirements(long user_id) {
        // Appel de la méthode pour charger l'historique
        getHistoVirements(user_id);
        return historiqueVirements;
    }
    public LiveData<LoggedInUserModel> getLoggedInUser() {
        return loggedInUser;
    }

    public void refreshUser() {
        // TODO: mettre à jour les données du User (le montant/solde)

    }

    public void getHistoVirements(long user_id) {
        //TODO: récupérer le code depuis le HistoriqueViewModel
        // Simulation de l'obtention de l'historique des virements depuis une source de données
        ArrayList<HistoriqueModel> historique = new ArrayList<>();
        // Ajoutez plus d'objets Virement selon vos besoins
        RetrofitService retrofitService = new RetrofitService();
        SepaApi sepaApi = retrofitService.getRetrofit().create(SepaApi.class);
        Call<List<HistoriqueModel>> callAsync = sepaApi.getHistoriqueUser(user_id);
        callAsync.enqueue(new Callback<List<HistoriqueModel>>() {
            @Override
            public void onResponse(Call<List<HistoriqueModel>> call, Response<List<HistoriqueModel>> response) {
                if (response.body().size()!=0){
                    for (HistoriqueModel hist:response.body()) {
                        historique.add(new HistoriqueModel(hist.getTransactionDate(), hist.getMotif(), hist.getAmount(), hist.getMode(), hist.getDestinataire()));
                        Logger.getLogger(AccueilActivity.class.getName()).log(Level.INFO, hist.toString());
                    }

                } else Logger.getLogger(AccueilActivity.class.getName()).log(Level.INFO,"No credit historic for this user");
            }
            @Override
            public void onFailure(Call<List<HistoriqueModel>> call, Throwable t) {
                Logger.getLogger(AccueilActivity.class.getName()).log(Level.SEVERE,"failed to fetch data");
                Logger.getLogger(AccueilActivity.class.getName()).log(Level.SEVERE,t.toString());
            }
        });
        // Mettez à jour le LiveData avec l'historique obtenu
        historiqueVirements.setValue(historique);
    }

}