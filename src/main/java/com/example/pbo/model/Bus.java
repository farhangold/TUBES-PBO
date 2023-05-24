package com.example.pbo.model;

public class Bus extends Kendaraan{
    public Bus(String nama) {
        super(nama);
        this.harga = 5000;
    }

    @Override
    public void setBerat(int berat) {
        this.berat =berat;
    }

    @Override
    public int getBerat() {
        return this.berat;
    }
}
