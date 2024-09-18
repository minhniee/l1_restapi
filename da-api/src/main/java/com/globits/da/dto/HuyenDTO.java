package com.globits.da.dto;

import java.util.List;

public class HuyenDTO {
    private int id;
    private String huyen;
    private List<XaDTO> xa;

    public String getHuyen() {
        return huyen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setHuyen(String huyen) {
        this.huyen = huyen;
    }

    public List<XaDTO> getXa() {
        return xa;
    }

    public void setXa(List<XaDTO> xa) {
        this.xa = xa;
    }
}
