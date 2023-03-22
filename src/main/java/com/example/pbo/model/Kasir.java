package com.example.pbo.model;

import java.util.ArrayList;

public class Kasir {
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
}
