package com.ensicaen.sepa_vue_2.data.model;

import lombok.NonNull;

public class PhoneCredit {
        private String ibanOrder;
        private String benefPhoneNumber ;
        private double amount;
        private String motif;

        public double getAmount() {
                return amount;
        }

        public String getIbanOrder() {
                return ibanOrder;
        }

        public String getBenefPhoneNumber() {
                return benefPhoneNumber;
        }

        public void setBenefPhoneNumber(String benefPhoneNumber) {
                this.benefPhoneNumber = benefPhoneNumber;
        }

        public void setAmount(double amount) {
                this.amount = amount;
        }

        public String getMotif() {
                return motif;
        }

        public void setMotif(String motif) {
                this.motif = motif;
        }

        public void setIbanOrder(String ibanOrder) {
                this.ibanOrder = ibanOrder;
        }
        @Override
        public String toString() {
                return "PhoneCredit{" +
                        "ibanOrder='" + ibanOrder + '\'' +
                        ", benefPhoneNumber='" + benefPhoneNumber + '\'' +
                        ", amount=" + amount +
                        ", motif='" + motif + '\'' +
                        '}';
        }

}
