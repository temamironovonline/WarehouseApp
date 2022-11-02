package com.example.warehouseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private WarehouseAdapter warehouseAdapter;
    private final ArrayList<Warehouse> listWarehouse = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView warehouseProducts = findViewById(R.id.listProduct);
        warehouseAdapter = new WarehouseAdapter(MainActivity.this, listWarehouse);
        warehouseProducts.setAdapter(warehouseAdapter);
    }

    private class GetProducts extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids)
        {
            try{
                URL url = new URL("http://10.0.2.2:8090/api/Warehouses");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder result = new StringBuilder();
                String line = "";

                while((line = reader.readLine()) != null){
                    result.append(line);
                }
                return result.toString();
            }
            catch (Exception exception)
            {
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s)
        {
            super.onPostExecute(s);
            try{
                JSONArray tempArray = new JSONArray(s);
                for (int i = 0; i < tempArray.length();i++)
                {
                    JSONObject productJson = tempArray.getJSONObject(i);

                }
            }
            catch (Exception exception){

            }
        }
    }
}