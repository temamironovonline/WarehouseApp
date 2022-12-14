package com.example.warehouseapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class WarehouseAdapter extends BaseAdapter {

    Context context;
    LayoutInflater layoutInflater;
    ArrayList<Warehouse> objects;

    WarehouseAdapter(Context context, ArrayList<Warehouse> objects){
        this.context = context;
        this.objects = objects;
        layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.warehouse_list_item, parent, false);
        }
        Warehouse c = getWarehouse(position);

        ((TextView) view.findViewById(R.id.showNameProduct)).setText(c.nameProduct);
        ((TextView) view.findViewById(R.id.showTypeProduct)).setText(c.typeProduct);
        ((TextView) view.findViewById(R.id.showCountProduct)).setText(c.countProduct);
        ((TextView) view.findViewById(R.id.showPriceProduct)).setText(c.priceProduct);
       // ((ImageView) view.findViewById(R.id.imageView)).setImageBitmap(c.photoCar);

        return view;
    }

    Warehouse getWarehouse(int position){
        return ((Warehouse) getItem(position));
    }

}
