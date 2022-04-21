package com.example.bookshop.Models;

public class Vong {
    int IDTK;
    float VONG;
    int SOSAO;

    public int getIDTK() {
        return IDTK;
    }

    public void setIDTK(int IDTK) {
        this.IDTK = IDTK;
    }

    public float getVONG() {
        return VONG;
    }

    public void setVONG(float VONG) {
        this.VONG = VONG;
    }

    public int getSOSAO() {
        return SOSAO;
    }

    public void setSOSAO(int SOSAO) {
        this.SOSAO = SOSAO;
    }

    public Vong(int IDTK, float VONG, int SOSAO) {
        this.IDTK = IDTK;
        this.VONG = VONG;
        this.SOSAO = SOSAO;
    }
    public Vong(float VONG, int SOSAO) {
        this.VONG = VONG;
        this.SOSAO = SOSAO;
    }
}
