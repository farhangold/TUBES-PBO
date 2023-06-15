package com.example.pbo.model;

public class Mobil extends Kendaraan{
    private float hargasat = 5000;

    public Mobil(String plat, String jenis, String start, int harga, String end) {
        super(plat, jenis, start, harga, end);
    }
    public Mobil(){
    }

    public float getHargasat() {
        return hargasat;
    }
}
