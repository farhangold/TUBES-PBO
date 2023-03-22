package com.example.pbo.model;

import java.time.LocalDateTime;

public class Transaksi {
    //field
    private String id_transaksi;
    private LocalDateTime endParkir;
    private int totalbayar = 0;
    private Pengemudi pengemudi;

    public String getId_transaksi() {
        return id_transaksi;
    }

    public void setId_transaksi(String id_transaksi) {
        this.id_transaksi = id_transaksi;
    }

    public LocalDateTime getEndParkir() {
        return endParkir;
    }

    public void setEndParkir(LocalDateTime endParkir) {
        this.endParkir = endParkir;
    }

    public int getTotalbayar() {
        return totalbayar;
    }

    public void setTotalbayar(int totalbayar) {
        this.totalbayar = totalbayar;
    }

    public Pengemudi getPengemudi() {
        return pengemudi;
    }

    public void setPengemudi(Pengemudi pengemudi) {
        this.pengemudi = pengemudi;
    }
}
