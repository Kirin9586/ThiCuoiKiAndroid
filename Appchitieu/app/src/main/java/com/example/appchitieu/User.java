package com.example.appchitieu;

import java.io.Serializable;

public class User implements Serializable {
    public User(int ID, String msotien, String mloai, String mdiadiem, String mthoigian, String mghichu){

    }

    public User() {

    }

    public int getSotienchitieu() {
        return sotienchitieu;
    }

    public void setSotienchitieu(int sotienchitieu) {
        this.sotienchitieu = sotienchitieu;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public String getDiadiem() {
        return diadiem;
    }

    public void setDiadiem(String diadiem) {
        this.diadiem = diadiem;
    }

    public String getThoigian() {
        return thoigian;
    }

    public void setThoigian(String thoigian) {
        this.thoigian = thoigian;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    private int ID;
    private int sotienchitieu;
    private String loai;
    private String diadiem;
    private String thoigian;
    private String ghichu;
    public User(int msotien,String mloai,String mdiadiem,String mthoigian,String mghichu){
        this.sotienchitieu = msotien;
        this.loai = mloai;
        this.diadiem = mdiadiem;
        this.thoigian = mthoigian;
        this.ghichu = mghichu;
    }

}
