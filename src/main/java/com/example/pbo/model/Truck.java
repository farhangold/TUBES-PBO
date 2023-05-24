package com.example.pbo.model;

public class Truck extends Kendaraan{

    public Truck(String nama) {
        super(nama);
        this.harga = 8000;
    }

    @Override
    public void setBerat(int berat) {
        this.berat = berat;
    }
    @Override
    public int getBerat() {
        return this.berat;
    }
}