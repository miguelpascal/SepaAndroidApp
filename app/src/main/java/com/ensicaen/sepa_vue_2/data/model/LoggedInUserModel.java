package com.ensicaen.sepa_vue_2.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUserModel implements Parcelable {
    private long customerID;
    private String lastName;
    private  String iban;
    private String bic;
    private double balance;
    private String currency;
    private String firstName;

    public LoggedInUserModel(Long userId, String lastName, String surName, String iban, String bic, double amount, String currency) {
        this.customerID =userId;
        this.lastName = lastName;
        this.firstName =surName;
        this.iban =iban;
        this.bic =bic;
        this.balance =amount;
        this.currency =currency;
    }

    public static final Creator<LoggedInUserModel> CREATOR = new Creator<LoggedInUserModel>() {
        @Override
        public LoggedInUserModel createFromParcel(Parcel in) {
            return new LoggedInUserModel(in);
        }

        @Override
        public LoggedInUserModel[] newArray(int size) {
            return new LoggedInUserModel[size];
        }
    };

    public Long getUserId() {
        return customerID;
    }

    public String getLastName() {
        return lastName;
    }

    public double getBalance() {
        return balance;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel out, int flags) {
        out.writeLong(customerID);
        out.writeString(lastName);
        out.writeString(iban);
        out.writeString(bic);
        out.writeDouble(balance);
        out.writeString(currency);
        out.writeString(firstName);
    }

    public LoggedInUserModel(Parcel in) {
        customerID = in.readLong();
        lastName = in.readString();
        iban = in.readString();
        bic = in.readString();
        balance = in.readDouble();
        currency = in.readString();
        firstName = in.readString();
    }
}
