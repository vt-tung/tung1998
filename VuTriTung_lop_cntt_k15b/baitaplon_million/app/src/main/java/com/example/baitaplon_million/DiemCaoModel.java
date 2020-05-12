package com.example.baitaplon_million;

public class DiemCaoModel {
    int id;
    String ten;
    String tien;

    public DiemCaoModel(int id, String ten, String tien) {
        this.id = id;
        this.ten = ten;
        this.tien = tien;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getTien() {
        return tien;
    }

    public void setTien(String tien) {
        this.tien = tien;
    }
}
