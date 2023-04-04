package com.example.pbo.model;

public class Motor extends Kendaraan {

    public Motor(String nama) {
        super(nama);
        this.harga = 2000;
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
