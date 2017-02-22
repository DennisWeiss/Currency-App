package com.example.weiss.test;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    public EditText money1;
    public EditText money2;
    public Currency firstCurrency;
    public Currency secondCurrency;
    public double firstMoneySet;
    public String jsonString = null;

    public ArrayAdapter<CharSequence> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = ArrayAdapter.createFromResource(this,
                R.array.currency_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        final Spinner currency1 = (Spinner) findViewById(R.id.spinner2);
        final Spinner currency2 = (Spinner) findViewById(R.id.spinner3);

        currency1.setAdapter(adapter);
        currency2.setAdapter(adapter);

        int spinnerPosition1 = adapter.getPosition("Euro");
        currency1.setSelection(spinnerPosition1);
        int spinnerPosition2 = adapter.getPosition("US Dollar");
        currency2.setSelection(spinnerPosition2);

        money1 = (EditText) findViewById(R.id.editText);

        new JSONTask().execute("http://api.fixer.io/latest");

        ImageButton swapbtn = (ImageButton) findViewById(R.id.imageButton);
        money2 = (EditText) findViewById(R.id.editText2);

        money1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                money2.setText(getMoney(currency1, currency2, money1));
            }
        });

        currency1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                money2.setText(getMoney(currency1, currency2, money1));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        currency2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                money2.setText(getMoney(currency1, currency2, money1));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        swapbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            swapCurrencies(currency1, currency2);
            }
        });
    }

    String getMoney(Spinner currency1, Spinner currency2, EditText money1) {
        firstCurrency = CurrencyGetter.setCurrency(currency1);
        secondCurrency = CurrencyGetter.setCurrency(currency2);
        if (money1.getText().toString().equals("")) {
            return "";
        } else {
            firstMoneySet = Double.valueOf(money1.getText().toString());
        }

        JSONObject obj = null;
        JSONObject rates = null;

        try {
            obj = new JSONObject(jsonString);
            rates = obj.getJSONObject("rates");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            firstCurrency.rateToEUR = rates.getDouble(firstCurrency.code);
        } catch (JSONException e) {
            firstCurrency.rateToEUR = 1.0;
            e.printStackTrace();
        }
        try {
            secondCurrency.rateToEUR = rates.getDouble(secondCurrency.code);
        } catch (JSONException e) {
            secondCurrency.rateToEUR = 1.0;
            e.printStackTrace();
        }

        double money;

        try{
            money = Math.round(firstMoneySet * secondCurrency.rateToEUR / firstCurrency.rateToEUR
                    * 100) / 100.0;
            return ensureTwoDecimals(money);
        } catch (NumberFormatException e) {

        }
        return "";
    }

    void swapCurrencies(Spinner currency1, Spinner currency2) {
        String temp = currency1.getSelectedItem().toString();
        currency1.setSelection(adapter.getPosition(currency2.getSelectedItem().toString()));
        currency2.setSelection(adapter.getPosition(temp));

        String temp2 = money1.getText().toString();
        money1.setText(money2.getText().toString());
        money2.setText(temp2);
    }

    static String ensureTwoDecimals(double money) {
        double tenTimesMoney = 10 * money;
        if (tenTimesMoney == (int)tenTimesMoney) {
            return Double.toString(money)+"0";
        } else {
            return Double.toString(money);
        }
    }

    public class JSONTask extends AsyncTask<String, String, String> {

        @Override
        protected  String doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            String line;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();

                line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                jsonString = buffer.toString();

                return buffer.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {

        }
    }
}
