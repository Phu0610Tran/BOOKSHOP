package com.example.bookshop.DTO;

public class GioHang {
    int IDGIOHANG;
    int IDSP;
    String TENSANPHAM;
    int IDTK;
    int SOLUONG;
    int THANHTIEN;

    public GioHang(int IDGIOHANG, int IDSP, String TENSANPHAM, int IDTK, int SOLUONG, int THANHTIEN) {
        this.IDGIOHANG = IDGIOHANG;
        this.IDSP = IDSP;
        this.TENSANPHAM = TENSANPHAM;
        this.IDTK = IDTK;
        this.SOLUONG = SOLUONG;
        this.THANHTIEN = THANHTIEN;
    }

    public int getIDGIOHANG() {
        return IDGIOHANG;
    }

    public void setIDGIOHANG(int IDGIOHANG) {
        this.IDGIOHANG = IDGIOHANG;
    }

    public int getIDSP() {
        return IDSP;
    }

    public void setIDSP(int IDSP) {
        this.IDSP = IDSP;
    }

    public String getTENSANPHAM() {
        return TENSANPHAM;
    }

    public void setTENSANPHAM(String TENSANPHAM) {
        this.TENSANPHAM = TENSANPHAM;
    }

    public int getIDTK() {
        return IDTK;
    }

    public void setIDTK(int IDTK) {
        this.IDTK = IDTK;
    }

    public int getSOLUONG() {
        return SOLUONG;
    }

    public void setSOLUONG(int SOLUONG) {
        this.SOLUONG = SOLUONG;
    }

    public int getTHANHTIEN() {
        return THANHTIEN;
    }

    public void setTHANHTIEN(int THANHTIEN) {
        this.THANHTIEN = THANHTIEN;
    }
}
