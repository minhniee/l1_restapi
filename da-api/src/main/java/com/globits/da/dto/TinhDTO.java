package com.globits.da.dto;

import java.util.List;

public class TinhDTO {
    private int id;
    private String tinh;
    private List<HuyenDTO> huyen;

    public String getTinh() {
        return tinh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTinh(String tinh) {
        this.tinh = tinh;
    }

    public List<HuyenDTO> getHuyen() {
        return huyen;
    }

    public void setHuyen(List<HuyenDTO> huyen) {
        this.huyen = huyen;
    }
}
