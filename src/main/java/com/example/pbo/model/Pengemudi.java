package com.example.pbo.model;

import java.time.LocalDateTime;

public class Pengemudi {
    private String jenisKendaraan;
    private String PlatNomor;
    private LocalDateTime startParkir;
    private Transaksi transaksi;
    //method

    public Pengemudi(String jenisKendaraan, String PlatNomor) {
        this.jenisKendaraan = jenisKendaraan;
        this.PlatNomor = PlatNomor;
    }

    public void setJenisKendaraan(String jenisKendaraan) {
        this.jenisKendaraan = jenisKendaraan;
    }

    public void setPlatNomor(String PlatNomor) {
        this.PlatNomor = PlatNomor;
    }

    public void setStartParkir(LocalDateTime startParkir) {
        this.startParkir = startParkir;
    }

    public String getJenisKendaraan() {
        return jenisKendaraan;
    }

    public String getPlatNomor() {
        return PlatNomor;
    }

    public LocalDateTime getStartParkir() {
        return startParkir;
    }
}
