package com.example.weiss.test;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private EditText tvData;
    public Currency firstCurrency;
    public Currency secondCurrency;
    public double firstMoneySet;
    public String jsonString = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
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

        final EditText money1 = (EditText) findViewById(R.id.editText);

        new JSONTask().execute("http://api.fixer.io/latest");

        Button btnHit = (Button) findViewById(R.id.button2);
        tvData = (EditText) findViewById(R.id.editText2);

        btnHit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                firstCurrency = CurrencyGetter.setCurrency(currency1);
                secondCurrency = CurrencyGetter.setCurrency(currency2);
                if (money1.getText().toString() == null) {
                    firstMoneySet = 0;
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

                double money = 0.0;

                try{
                    money = Math.round(firstMoneySet * secondCurrency.rateToEUR / firstCurrency.rateToEUR
                            * 100) / 100.0;
                    tvData.setText(ensureTwoDecimals(money));
                } catch (NumberFormatException e) {

                }
            }
        });
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
            String line = null;

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
