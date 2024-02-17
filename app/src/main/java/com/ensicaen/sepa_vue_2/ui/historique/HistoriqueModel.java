package com.ensicaen.sepa_vue_2.ui.historique;

public class HistoriqueModel {
    private final String date;
    private final String motif;
    private final String montant;
    private final String destinataire;

    public HistoriqueModel(String date, String motif, String montant, String destinataire) {
        this.date = date;
        this.motif = motif;
        this.montant = montant;
        this.destinataire = destinataire;
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
    public String getDestinataire() {   return destinataire;    }
}
