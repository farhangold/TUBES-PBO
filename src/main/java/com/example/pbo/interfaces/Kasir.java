package com.example.pbo.interfaces;

import com.example.pbo.model.Pengemudi;

public interface Kasir {

    public Pengemudi getPengemudiByPlatNomor();
    public double getTotalBayar(String plat,String jenisKendaraan);
    public  String getNoTransaksi(String notransaksi);
}
