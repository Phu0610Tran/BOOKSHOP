package com.example.bookshop.Models;

public class HoaDon {
    int IDHOADON,TONGTIEN,IDCTHOADON;
    String GHICHU,DIACHI;
    int IDTAIKHOAN;
    int TIENGIAM,TIENSHIP,HTVC;
    String NgayDat;
    int TINHTRANG;



    public HoaDon(int IDHOADON, int TONGTIEN, int IDCTHOADON, String GHICHU, String DIACHI, int IDTAIKHOAN, int TIENGIAM, int TIENSHIP, int HTVC, String ngayDat, int TINHTRANG) {
        this.IDHOADON = IDHOADON;
        this.TONGTIEN = TONGTIEN;
        this.IDCTHOADON = IDCTHOADON;
        this.GHICHU = GHICHU;
        this.DIACHI = DIACHI;
        this.IDTAIKHOAN = IDTAIKHOAN;
        this.TIENGIAM = TIENGIAM;
        this.TIENSHIP = TIENSHIP;
        this.HTVC = HTVC;
        NgayDat = ngayDat;
        this.TINHTRANG = TINHTRANG;
    }

    public HoaDon(int IDHOADON, int TONGTIEN, int IDCTHOADON, String GHICHU, String DIACHI, int IDTAIKHOAN) {
        this.IDHOADON = IDHOADON;
        this.TONGTIEN = TONGTIEN;
        this.IDCTHOADON = IDCTHOADON;
        this.GHICHU = GHICHU;
        this.DIACHI = DIACHI;
        this.IDTAIKHOAN = IDTAIKHOAN;
    }

    public String getNgayDat() {
        return NgayDat;
    }

    public void setNgayDat(String ngayDat) {
        NgayDat = ngayDat;
    }
    public int getTIENGIAM() {
        return TIENGIAM;
    }

    public void setTIENGIAM(int TIENGIAM) {
        this.TIENGIAM = TIENGIAM;
    }

    public int getTIENSHIP() {
        return TIENSHIP;
    }

    public void setTIENSHIP(int TIENSHIP) {
        this.TIENSHIP = TIENSHIP;
    }

    public int getHTVC() {
        return HTVC;
    }

    public void setHTVC(int HTVC) {
        this.HTVC = HTVC;
    }

    public int getIDHOADON() {
        return IDHOADON;
    }

    public void setIDHOADON(int IDHOADON) {
        this.IDHOADON = IDHOADON;
    }

    public int getTONGTIEN() {
        return TONGTIEN;
    }

    public void setTONGTIEN(int TONGTIEN) {
        this.TONGTIEN = TONGTIEN;
    }

    public int getIDCTHOADON() {
        return IDCTHOADON;
    }

    public void setIDCTHOADON(int IDCTHOADON) {
        this.IDCTHOADON = IDCTHOADON;
    }

    public String getGHICHU() {
        return GHICHU;
    }

    public void setGHICHU(String GHICHU) {
        this.GHICHU = GHICHU;
    }

    public String getDIACHI() {
        return DIACHI;
    }

    public void setDIACHI(String DIACHI) {
        this.DIACHI = DIACHI;
    }

    public int getIDTAIKHOAN() {
        return IDTAIKHOAN;
    }

    public void setIDTAIKHOAN(int IDTAIKHOAN) {
        this.IDTAIKHOAN = IDTAIKHOAN;
    }

    public int getTINHTRANG() {
        return TINHTRANG;
    }

    public void setTINHTRANG(int TINHTRANG) {
        this.TINHTRANG = TINHTRANG;
    }
}
