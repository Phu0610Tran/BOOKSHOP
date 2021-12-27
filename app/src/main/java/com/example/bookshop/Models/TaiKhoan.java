package com.example.bookshop.Models;

public class TaiKhoan {
    int MATK;
    String TENTK;
    String MATKHAU;
    int SDT;
    String EMAIL;
    String NGAYSINH;
    String DIACHI;
    int MAQUYEN;
    byte[] HINHANH;

    public byte[] getHINHANH() {
        return HINHANH;
    }

    public void setHINHANH(byte[] HINHANH) {
        this.HINHANH = HINHANH;
    }

    public TaiKhoan(int MATK, String TENTK, String MATKHAU, int SDT, String EMAIL, String NGAYSINH, int MAQUYEN, String DIACHI, byte[] HINHANH) {
        this.MATK = MATK;
        this.TENTK = TENTK;
        this.MATKHAU = MATKHAU;
        this.SDT = SDT;
        this.EMAIL = EMAIL;
        this.NGAYSINH = NGAYSINH;
        this.DIACHI = DIACHI;
        this.MAQUYEN = MAQUYEN;
        this.HINHANH = HINHANH;
    }

    public TaiKhoan() {
        MATK=-1;
    }

//    public TaiKhoan(int MATK, String TENTK, String MATKHAU, int SDT, String EMAIL, String NGAYSINH, int MAQUYEN, String DIACHI) {
//        this.MATK = MATK;
//        this.TENTK = TENTK;
//        this.MATKHAU = MATKHAU;
//        this.SDT = SDT;
//        this.EMAIL = EMAIL;
//        this.NGAYSINH = NGAYSINH;
//        this.MAQUYEN = MAQUYEN;
//        this.DIACHI = DIACHI;
//    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public int getMATK() {
        return MATK;
    }

    public void setMATK(int MATK) {
        this.MATK = MATK;
    }

    public int getMAQUYEN() {
        return MAQUYEN;
    }

    public void setMAQUYEN(int MAQUYEN) {
        this.MAQUYEN = MAQUYEN;
    }

    public String getTENTK() {
        return TENTK;
    }

    public void setTENTK(String TENTK) {
        this.TENTK = TENTK;
    }

    public String getMATKHAU() {
        return MATKHAU;
    }

    public void setMATKHAU(String MATKHAU) {
        this.MATKHAU = MATKHAU;
    }

    public String getNGAYSINH() {
        return NGAYSINH;
    }

    public void setNGAYSINH(String NGAYSINH) {
        this.NGAYSINH = NGAYSINH;
    }

    public int getSDT() {
        return SDT;
    }

    public void setSDT(int SDT) {
        this.SDT = SDT;
    }

    public String getDIACHI() {
        return DIACHI;
    }

    public void setDIACHI(String DIACHI) {
        this.DIACHI = DIACHI;
    }

}
