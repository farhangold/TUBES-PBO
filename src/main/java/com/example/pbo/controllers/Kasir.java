package com.example.pbo.controllers;

import com.example.pbo.model.Pengemudi;

public interface Kasir {

    Pengemudi getPengemudiByPlatNomor();
    void getTotalBayar(String plat,String jenisKendaraan);
    void getNoTransaksi(String notransaksi);
}
