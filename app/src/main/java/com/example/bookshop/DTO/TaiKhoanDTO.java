package com.example.bookshop.DTO;

public class TaiKhoanDTO {
    int MATK;
    int SDT;
    int MAQUYEN;
    String TENTK;
    String MATKHAU;
    String NGAYSINH;
    String EMAIL;

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

}