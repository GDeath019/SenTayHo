package com.example.thi1;

public class HoaDon {
    int Ma;
    Double DonGia;
    String BienSoXe;
    Double QuangDuong, GiamGia;

    public HoaDon(int ma, String bienSoXe, Double quangDuong, Double donGia, Double giamgia) {
        Ma = ma;
        DonGia = donGia;
        BienSoXe = bienSoXe;
        QuangDuong = quangDuong;
        GiamGia = giamgia;
    }

    public HoaDon() {
    }

    public Double getGiamGia() {
        return GiamGia;
    }

    public void setGiamGia(Double giamGia) {
        GiamGia = giamGia;
    }

    public int getMa() {
        return Ma;
    }

    public void setMa(int ma) {
        Ma = ma;
    }

    public Double getDonGia() {
        return DonGia;
    }

    public void setDonGia(Double donGia) {
        DonGia = donGia;
    }

    public String getBienSoXe() {
        return BienSoXe;
    }

    public void setBienSoXe(String bienSoXe) {
        BienSoXe = bienSoXe;
    }

    public Double getQuangDuong() {
        return QuangDuong;
    }

    public void setQuangDuong(Double quangDuong) {
        QuangDuong = quangDuong;
    }
}
