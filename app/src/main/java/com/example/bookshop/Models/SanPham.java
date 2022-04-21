package com.example.bookshop.Models;

public class SanPham {

    int MaSP;
    byte[] ImageSP;
    String TenSP;
    int GiaSP;
    int Sl_SP;
    String MotaSP;
    int IDDANHMUC;
    int SPNEW;




    public SanPham(int maSP, byte[] imageSP, String tenSP, int giaSP, int sl_SP, int IDDANHMUC, int SPNEW) {
        MaSP = maSP;
        ImageSP = imageSP;
        TenSP = tenSP;
        GiaSP = giaSP;
        Sl_SP = sl_SP;
        this.IDDANHMUC = IDDANHMUC;
        this.SPNEW = SPNEW;
    }
    public SanPham(int maSP, byte[] imageSP, String tenSP, int giaSP, int sl_SP,String motaSP) {
        MaSP = maSP;
        ImageSP = imageSP;
        TenSP = tenSP;
        GiaSP = giaSP;
        Sl_SP = sl_SP;
        this.MotaSP = motaSP;
    }
    public int getIDDANHMUC() {
        return IDDANHMUC;
    }

    public void setIDDANHMUC(int IDDANHMUC) {
        this.IDDANHMUC = IDDANHMUC;
    }

    public int getSPNEW() {
        return SPNEW;
    }

    public void setSPNEW(int SPNEW) {
        this.SPNEW = SPNEW;
    }
    public int getMaSP() {
        return MaSP;
    }

    public void setMaSP(int maSP) {
        MaSP = maSP;
    }

    public byte[] getImageSP() {
        return ImageSP;
    }

    public void setImageSP(byte[] imageSP) {
        ImageSP = imageSP;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String tenSP) {
        TenSP = tenSP;
    }

    public int getGiaSP() {
        return GiaSP;
    }

    public void setGiaSP(int giaSP) {
        GiaSP = giaSP;
    }

    public int getSl_SP() {
        return Sl_SP;
    }

    public void setSl_SP(int sl_SP) {
        Sl_SP = sl_SP;
    }

    public String getMotaSP() {
        return MotaSP;
    }

    public void setMotaSP(String motaSP) {
        MotaSP = motaSP;
    }



}
