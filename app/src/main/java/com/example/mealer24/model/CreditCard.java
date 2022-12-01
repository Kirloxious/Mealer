package com.example.mealer24.model;

/**
 * Classe CreditCard
 * This class stores a users credit card information
 */
public class CreditCard {
    private String creditCardNumber;
    private String creditCardExp;
    private String creditCardCVV;

    public CreditCard(String creditCardNumber, String creditCardExp, String creditCardCVV){
        this.creditCardNumber = creditCardNumber;
        this.creditCardExp = creditCardExp;
        this.creditCardCVV = creditCardCVV;


    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getCreditCardExp() {
        return creditCardExp;
    }

    public void setCreditCardExp(String creditCardExp) {
        this.creditCardExp = creditCardExp;
    }

    public String getCreditCardCVV() {
        return creditCardCVV;
    }

    public void setCreditCardCVV(String creditCardCVV) {
        this.creditCardCVV = creditCardCVV;
    }
}
