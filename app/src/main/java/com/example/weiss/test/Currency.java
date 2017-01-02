package com.example.weiss.test;

/**
 * Created by weiss on 03.12.2016.
 */

public class Currency {
    String name;
    String code;
    double rateToEUR;

    public Currency(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public void setCurrency(String name, String code) {
        this.name = name;
        this.code = code;
    }
}