package com.example.weiss.test;

import android.widget.Spinner;

/**
 * Created by weiss on 03.12.2016.
 */

public class CurrencyGetter {
    public static Currency setCurrency(Spinner currency1) {
        Currency firstCurrency = new Currency("Euro", "EUR");

        if (currency1.getSelectedItem().toString().equals("Euro")) {
            firstCurrency.setCurrency("Euro", "EUR");
        } else if (currency1.getSelectedItem().toString().equals("US Dollar")) {
            firstCurrency.setCurrency("US Dollar", "USD");
        } else if (currency1.getSelectedItem().toString().equals("British Pound")) {
            firstCurrency.setCurrency("British pound", "GBP");
        } else if (currency1.getSelectedItem().toString().equals("Swiss Franc")) {
            firstCurrency.setCurrency("Swiss Franc", "CHF");
        } else if (currency1.getSelectedItem().toString().equals("Australian Dollar")) {
            firstCurrency.setCurrency("Australian Dollar", "AUD");
        } else if (currency1.getSelectedItem().toString().equals("Bulgarian Lev")) {
            firstCurrency.setCurrency("Bulgarian Lev", "BGN");
        } else if (currency1.getSelectedItem().toString().equals("Brazilian Real")) {
            firstCurrency.setCurrency("Brazilian Real", "BRL");
        } else if (currency1.getSelectedItem().toString().equals("Canadian Dollar")) {
            firstCurrency.setCurrency("Canadian Dollar", "CAD");
        } else if (currency1.getSelectedItem().toString().equals("Chinese Yuan")) {
            firstCurrency.setCurrency("Chinese Yuan", "CNY");
        } else if (currency1.getSelectedItem().toString().equals("Czech Republic Koruna")) {
            firstCurrency.setCurrency("Czech Republic Koruna", "CZK");
        } else if (currency1.getSelectedItem().toString().equals("Danish Krone")) {
            firstCurrency.setCurrency("Danish Krone", "DKK");
        } else if (currency1.getSelectedItem().toString().equals("Hong Kong Dollar")) {
            firstCurrency.setCurrency("Hong Kong Dollar", "HKD");
        } else if (currency1.getSelectedItem().toString().equals("Croatian Kuna")) {
            firstCurrency.setCurrency("Croatian Kuna", "HRK");
        } else if (currency1.getSelectedItem().toString().equals("Hungarian Forint")) {
            firstCurrency.setCurrency("Hungarian Forint", "HUF");
        } else if (currency1.getSelectedItem().toString().equals("Indonesian Rupiah")) {
            firstCurrency.setCurrency("Indonesian Rupiah", "IDR");
        } else if (currency1.getSelectedItem().toString().equals("Israeli New Sheqel")) {
            firstCurrency.setCurrency("Israeli New Sheqel", "ILS");
        } else if (currency1.getSelectedItem().toString().equals("Indian Rupee")) {
            firstCurrency.setCurrency("Indian Rupee", "INR");
        } else if (currency1.getSelectedItem().toString().equals("Japanese Yen")) {
            firstCurrency.setCurrency("Japanese Yen", "JPY");
        } else if (currency1.getSelectedItem().toString().equals("South Korean Won")) {
            firstCurrency.setCurrency("South Korean Won", "KRW");
        } else if (currency1.getSelectedItem().toString().equals("Mexican Peso")) {
            firstCurrency.setCurrency("Mexican Peso", "MXN");
        } else if (currency1.getSelectedItem().toString().equals("Malaysian Ringgit")) {
            firstCurrency.setCurrency("Malaysian Ringgit", "MYR");
        } else if (currency1.getSelectedItem().toString().equals("Norwegian Krone")) {
            firstCurrency.setCurrency("Norwegian Krone", "NOK");
        } else if (currency1.getSelectedItem().toString().equals("New Zealand Dollar")) {
            firstCurrency.setCurrency("New Zealand Dollar", "NZD");
        } else if (currency1.getSelectedItem().toString().equals("Philippine Peso")) {
            firstCurrency.setCurrency("Philippine Peso", "PHP");
        } else if (currency1.getSelectedItem().toString().equals("Polish Zloty")) {
            firstCurrency.setCurrency("Polish Zloty", "PLN");
        } else if (currency1.getSelectedItem().toString().equals("Romanian Leu")) {
            firstCurrency.setCurrency("Romanian Leu", "RON");
        } else if (currency1.getSelectedItem().toString().equals("Russian Ruble")) {
            firstCurrency.setCurrency("Russian Ruble", "RUB");
        } else if (currency1.getSelectedItem().toString().equals("Swedish Krona")) {
            firstCurrency.setCurrency("Swedish Krona", "SEK");
        } else if (currency1.getSelectedItem().toString().equals("Singapore Dollar")) {
            firstCurrency.setCurrency("Singapore Dollar", "SGD");
        } else if (currency1.getSelectedItem().toString().equals("Thai Baht")) {
            firstCurrency.setCurrency("Thai Baht", "THB");
        } else if (currency1.getSelectedItem().toString().equals("Turkish Lira")) {
            firstCurrency.setCurrency("Turkish Lira", "TRY");
        } else if (currency1.getSelectedItem().toString().equals("South African Rand")) {
            firstCurrency.setCurrency("South African Rand", "ZAR");
        }

        return firstCurrency;
    }
}
