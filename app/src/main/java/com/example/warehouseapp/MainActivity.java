package com.example.warehouseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;


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
        new GetProducts().execute();
    }

    //Метод на изменение значения в спиннере цены
    public void forSpinnerColorPriceChanged(Spinner forSortByColorOrPrice){
        forSortByColorOrPrice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
               /* currentPriceSort = parentView.getSelectedItemPosition();
                if (currentColorSort.equals("Нет")) currentColorSort = "";
                if (currentPriceSort == 0)
                    query = String.format("select *, convert(varchar(max), photo_car) from cars where NAME_CAR like '%s%%' and COLOR_CAR like '%s%%'", searchDataChangeName.getText().toString(), currentColorSort);
                else if (currentPriceSort == 1)
                    query = String.format("select *, convert(varchar(max), photo_car) from cars where NAME_CAR like '%s%%' and COLOR_CAR like '%s%%' Order by CAR_PRICE asc", searchDataChangeName.getText().toString(), currentColorSort);
                else query = String.format("select *, convert(varchar(max), photo_car) from cars where NAME_CAR like '%s%%' and COLOR_CAR like '%s%%' Order by CAR_PRICE desc", searchDataChangeName.getText().toString(), currentColorSort);
                getListFromSQL();
                */


            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });
    }


    //Метод на создание спиннера для сортировки по цене и цвету
    public Spinner sortingDataByPrice(){
        String[] arraySpinnerPrice = new String[] {
                "Нет", "По возрастанию цены", "По убыванию цены", "По возрастанию количества", "По убыванию количества"
        };
        Spinner spinnerSortByPrice = (Spinner) findViewById(R.id.sortingCountOrColor);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinnerPrice);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSortByPrice.setAdapter(adapter);
        return spinnerSortByPrice;
    }




    private class GetProducts extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids)
        {
            try{
               URL url = new URL("http://10.0.2.2:8080/api/Warehouses");
                //URL url = new URL("http://localhost:55061/api/Warehouses");
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
                    Warehouse tempProduct = new Warehouse(
                            productJson.getInt("productId"),
                            productJson.getString("productName"),
                            productJson.getString("productType"),
                            productJson.getString("productCount"),
                            productJson.getString("productPrice")
                    );
                    listWarehouse.add(tempProduct);
                    warehouseAdapter.notifyDataSetChanged();
                }
            }
            catch (Exception exception){

            }
        }
    }
}