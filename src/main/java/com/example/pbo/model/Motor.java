package com.example.pbo.model;

public class Motor extends Kendaraan {
    private float hargasat = 2000;

    public  Motor(){

    }

    public Motor(String plat, String jenis, String start, int harga, String end) {
        super(plat, jenis, start, harga, end);
    }

    public float getHargasat() {
        return hargasat;
    }
}
