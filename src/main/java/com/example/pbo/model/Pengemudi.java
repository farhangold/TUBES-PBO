package com.example.pbo.model;

import java.time.LocalDateTime;

public class Pengemudi {
    private Kendaraan jenisKendaraan;
    private String PlatNomor;
    private LocalDateTime startParkir;
    //method

    public Pengemudi(String jenisKendaraan, String PlatNomor) {
        this.PlatNomor = PlatNomor;
    }

    public void setPlatNomor(String PlatNomor) {
        this.PlatNomor = PlatNomor;
    }

    public void setStartParkir(LocalDateTime startParkir) {
        this.startParkir = startParkir;
    }

    public String getPlatNomor() {
        return PlatNomor;
    }

    public LocalDateTime getStartParkir() {
        return startParkir;
    }
}
