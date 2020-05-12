package com.example.baitaplon_million;

public class tienThuongModel {
    int id;
    String tien;

    public tienThuongModel(int id, String tien) {
        this.id = id;
        this.tien = tien;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTien() {
        return tien;
    }

    public void setTien(String tien) {
        this.tien = tien;
    }
}
