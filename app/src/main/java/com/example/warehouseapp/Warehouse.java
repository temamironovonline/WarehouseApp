package com.example.warehouseapp;

import android.graphics.Bitmap;

public class Warehouse {
    int idProduct;
    String nameProduct;
    String typeProduct;
    int countProduct;
    int priceProduct;
    Bitmap photoProduct;

    Warehouse(int idProduct, String nameProduct, String typeProduct, int countProduct, int priceProduct, Bitmap photoProduct){
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.typeProduct = typeProduct;
        this.countProduct = countProduct;
        this.priceProduct = priceProduct;
        this.photoProduct = photoProduct;
    }
}
