package com.example.pbo.model;

public abstract  class Kendaraan{
    public String nama;
    public int berat;
    public double harga;

    public Kendaraan(String nama) {
        this.nama = nama;
    }

    public abstract void setBerat(int berat);
    public abstract int getBerat();
}