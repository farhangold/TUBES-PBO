package com.example.pbo.model;

public class Truck extends Kendaraan{
    private float hargasat = 30000;


    public Truck(String plat, String jenis, String start, int harga, String end) {
        super(plat, jenis, start, harga, end);
    }

    public Truck(){}

    public float getHargasat() {
        return hargasat;
    }
}
