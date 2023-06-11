package com.example.pbo.model;

public class Kendaraan{
    public String plat;
    public String jenis;
    public String start;
    public int harga;
    public String end;

    public Kendaraan() {

    }

    public Kendaraan(String plat, String jenis, String start, int harga, String end) {
        this.plat = plat;
        this.jenis = jenis;
        this.start = start;
        this.harga = harga;
        this.end = end;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public String getPlat() {
        return plat;
    }

    public void setPlat(String plat) {
        this.plat = plat;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
