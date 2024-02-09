package com.ensicaen.sepa_vue_2.ui.login;

/**
 * Class exposing authenticated user details to the UI.
 */
class LoggedInUserView {

    //data fields that may be accessible to the UI
    private Long userId;
    private String lastName;
    private  String iban;
    private String bic;
    private double amount;
    private String currency;
    private String firstName;

    public LoggedInUserView(Long userId, String lastName, String surName, String iban, String bic, double amount, String currency) {
        this.userId =userId;
        this.lastName = lastName;
        this.firstName =surName;
        this.iban =iban;
        this.bic =bic;
        this.amount =amount;
        this.currency =currency;
    }

    public Long getUserId() {
        return userId;
    }

    String getDisplayName() {
        return lastName;
    }
    public double getAmount() {
        return amount;
    }

    public String getIban() {
        return iban;
    }

    public String getBic() {
        return bic;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getCurrency() {
        return currency;
    }
    LoggedInUserView(String lastName) {
        this.lastName = lastName;
    }


}