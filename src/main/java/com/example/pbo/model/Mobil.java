package com.example.pbo.model;

public class Mobil extends Kendaraan{
    public Mobil(String nama) {
        super(nama);
        this.harga = 3000;
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
