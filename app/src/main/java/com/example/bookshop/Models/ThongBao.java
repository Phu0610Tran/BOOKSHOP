package com.example.bookshop.Models;

public class ThongBao {
    int IDTB;
    String TIEUDE;
    String NOIDUNG;
    String DATE;
    byte[] HINHANHTHONGBAO;
    int THICH;
    int KHONGTHICH;
    

    public ThongBao(int IDBV, String TIEUDE, String NOIDUNG, String DATE, byte[] HINHANHBAIVIET, int THICH, int KHONGTHICH) {
        this.IDTB = IDBV;
        this.TIEUDE = TIEUDE;
        this.NOIDUNG = NOIDUNG;
        this.DATE = DATE;
        this.HINHANHTHONGBAO = HINHANHBAIVIET;
        this.THICH = THICH;
        this.KHONGTHICH = KHONGTHICH;
    }
    public ThongBao(String TIEUDE, String NOIDUNG) {
        this.TIEUDE = TIEUDE;
        this.NOIDUNG = NOIDUNG;
    }

    public int getTHICH() {
        return THICH;
    }

    public void setTHICH(int THICH) {
        this.THICH = THICH;
    }

    public int getKHONGTHICH() {
        return KHONGTHICH;
    }

    public void setKHONGTHICH(int KHONGTHICH) {
        this.KHONGTHICH = KHONGTHICH;
    }

    public int getIDTB() {
        return IDTB;
    }

    public void setIDTB(int IDTB) {
        this.IDTB = IDTB;
    }

    public String getTIEUDE() {
        return TIEUDE;
    }

    public void setTIEUDE(String TIEUDE) {
        this.TIEUDE = TIEUDE;
    }

    public String getNOIDUNG() {
        return NOIDUNG;
    }

    public void setNOIDUNG(String NOIDUNG) {
        this.NOIDUNG = NOIDUNG;
    }

    public String getDATE() {
        return DATE;
    }

    public void setDATE(String DATE) {
        this.DATE = DATE;
    }

    public byte[] getHINHANHTHONGBAO() {
        return HINHANHTHONGBAO;
    }

    public void setHINHANHTHONGBAO(byte[] HINHANHTHONGBAO) {
        this.HINHANHTHONGBAO = HINHANHTHONGBAO;
    }

}
