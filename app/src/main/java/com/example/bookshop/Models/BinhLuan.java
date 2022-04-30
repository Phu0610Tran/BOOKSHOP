package com.example.bookshop.Models;

public class BinhLuan {
    Integer TaiKhoanBL;
    byte[] HinhBL;
    String NoidungBL;
    String ThoiGianBL;
    float Ra;
    String TENTAIKHOAN;



    public BinhLuan() {
    }
    public BinhLuan(Integer taiKhoanBL, byte[] hinhBL, String noidungBL, String thoiGianBL) {
        TaiKhoanBL = taiKhoanBL;
        HinhBL = hinhBL;
        NoidungBL = noidungBL;
        ThoiGianBL = thoiGianBL;
    }
    public BinhLuan(Integer taiKhoanBL, byte[] hinhBL, String noidungBL, String thoiGianBL,float Ra, String TENTAIKHOAN) {
        TaiKhoanBL = taiKhoanBL;
        HinhBL = hinhBL;
        NoidungBL = noidungBL;
        ThoiGianBL = thoiGianBL;
        this.Ra = Ra;
        this.TENTAIKHOAN = TENTAIKHOAN;
    }

    public String getTENTAIKHOAN() {
        return TENTAIKHOAN;
    }

    public void setTENTAIKHOAN(String TENTAIKHOAN) {
        this.TENTAIKHOAN = TENTAIKHOAN;
    }

    public float getRa() {
        return Ra;
    }

    public void setRa(float ra) {
        Ra = ra;
    }
    public Integer getTaiKhoanBL() {
        return TaiKhoanBL;
    }

    public void setTaiKhoanBL(Integer taiKhoanBL) {
        TaiKhoanBL = taiKhoanBL;
    }

    public byte[] getHinhBL() {
        return HinhBL;
    }

    public void setHinhBL(byte[] hinhBL) {
        HinhBL = hinhBL;
    }

    public String getNoidungBL() {
        return NoidungBL;
    }

    public void setNoidungBL(String noidungBL) {
        NoidungBL = noidungBL;
    }

    public String getThoiGianBL() {
        return ThoiGianBL;
    }

    public void setThoiGianBL(String thoiGianBL) {
        ThoiGianBL = thoiGianBL;
    }
}
