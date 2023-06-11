package com.example.pbo.model;

public class Bus extends Kendaraan{
    private float hargasat = 40000;

    public Bus(String plat, String jenis, String start, int harga, String end) {
        super(plat, jenis, start, harga, end);
    }
    public  Bus(){}

    public float getHargasat() {
        return hargasat;
    }
}
