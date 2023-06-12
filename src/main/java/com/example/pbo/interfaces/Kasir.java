package com.example.pbo.interfaces;

import com.example.pbo.model.Kendaraan;

import java.sql.ResultSet;

public interface Kasir {

    public ResultSet getPengemudiByPlatNomor();
    public void tambah_data();
    public void updatekendaraan();
}
