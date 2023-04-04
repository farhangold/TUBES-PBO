package com.example.pbo.model;

import java.util.ArrayList;

public class Kasir implements com.example.pbo.controllers.Kasir {
    private String id_pekerja;
    private ArrayList<Pengemudi> listparkir;
    //Method
    public Kasir(String id_pekerja) {
        this.id_pekerja = id_pekerja;
    }

    public String getId_pekerja() {
        return id_pekerja;
    }

    public void setId_pekerja(String id_pekerja) {
        this.id_pekerja = id_pekerja;
    }

    @Override
    public Pengemudi getPengemudiByPlatNomor() {
        return null;
    }

    @Override
    public double getTotalBayar(String plat, String jenisKendaraan) {
        return 0;
    }

    @Override
    public String getNoTransaksi(String notransaksi) {
        return null;
    }
}
