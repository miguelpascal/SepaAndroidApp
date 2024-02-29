package com.ensicaen.sepa_vue_2.exceptions;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ErrorHandlingSepaException {

    @SerializedName("errorMessage")
    @Expose
    private String errorMessage;
    @SerializedName("status")
    @Expose
    private String status;

    public String getErrorMessage() {
        return errorMessage;
    }

}
