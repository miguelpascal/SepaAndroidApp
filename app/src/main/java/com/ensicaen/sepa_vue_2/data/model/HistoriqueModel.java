package com.ensicaen.sepa_vue_2.data.model;

import androidx.annotation.NonNull;

import java.util.Date;

public class HistoriqueModel {

    private Long idOperation;
    private  String ibanOrder;
    private String mode;
    private String lastNameBenef;
    private String firstNameBenef;
    private final Date transactionDate;
    private final String motif;
    private final double amount;
    private final String destinataire;



    public HistoriqueModel(Date date, String motif, double montant, String mode, String destinataire) {
        this.transactionDate = date;
        this.motif = motif;
        this.amount = montant;
        this.mode = mode;
        this.destinataire = destinataire;
    }

    public Date getTransactionDate() {return transactionDate;}
    public String getMotif() {
        return motif;
    }
    public double getAmount() {
        return amount;
    }
    public String getDestinataire() {   return destinataire;    }

    public String getMode() {
        return mode;
    }

    @NonNull
    @Override
    public String toString() {
        return getMotif().toString()+"\n" + getDestinataire()+"\n" + getAmount() +"\n"+ getTransactionDate().toString();
    }
}
