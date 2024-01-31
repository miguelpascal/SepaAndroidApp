package com.ensicaen.sepa_vue_2.ui.historique;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;

public class HistoriqueViewModel extends ViewModel {

    private final MutableLiveData<ArrayList<HistoriqueModel>> historiqueVirements;
    private final MutableLiveData<String> mText;

    public HistoriqueViewModel() {
        historiqueVirements = new MutableLiveData<>();
        mText = new MutableLiveData<>();
        mText.setValue("Historique des virements");

        chargerHistoriqueVirements(); // Appel de la méthode pour charger l'historique
    }

    public LiveData<ArrayList<HistoriqueModel>> getHistoriqueVirements() {
        return historiqueVirements;
    }

    private void chargerHistoriqueVirements() {
        // Simulation de l'obtention de l'historique des virements depuis une source de données
        ArrayList<HistoriqueModel> historique = new ArrayList<>();

        historique.add(new HistoriqueModel("01/01/2024", "Motif 1", "100.00", "FR1234567890123456789012345"));
        historique.add(new HistoriqueModel("02/01/2024", "Motif 2", "150.50", "FR9876543210987654321098765"));
        historique.add(new HistoriqueModel("03/01/2024", "Motif 3", "75.25", "FR5678901234567890123456789"));

        historique.add(new HistoriqueModel("01/01/2024", "Motif 1", "100.00", "FR1234567890123456789012345"));
        historique.add(new HistoriqueModel("02/01/2024", "Motif 2", "150.50", "FR9876543210987654321098765"));
        historique.add(new HistoriqueModel("03/01/2024", "Motif 3", "75.25", "FR5678901234567890123456789"));

        historique.add(new HistoriqueModel("01/01/2024", "Motif 1", "100.00", "FR1234567890123456789012345"));
        historique.add(new HistoriqueModel("02/01/2024", "Motif 2", "150.50", "FR9876543210987654321098765"));
        historique.add(new HistoriqueModel("03/01/2024", "Motif 3", "75.25", "FR5678901234567890123456789"));
        // Ajoutez plus d'objets Virement selon vos besoins

        // Mettez à jour le LiveData avec l'historique obtenu
        historiqueVirements.setValue(historique);
    }



    public LiveData<String> getText() {
        return mText;
    }
}
