package com.ensicaen.sepa_vue_2.ui.historique;

public class HistoriqueModel {
    private final String date;
    private final String motif;
    private final String montant;
    private final String ibanDestinataire;

    public HistoriqueModel(String date, String motif, String montant, String ibanDestinataire) {
        this.date = date;
        this.motif = motif;
        this.montant = montant;
        this.ibanDestinataire = ibanDestinataire;
    }

    public String getDate() {
        return date;
    }

    public String getMotif() {
        return motif;
    }

    public String getMontant() {
        return montant;
    }
    public String getIbanDestinataire() {   return ibanDestinataire;    }
}
