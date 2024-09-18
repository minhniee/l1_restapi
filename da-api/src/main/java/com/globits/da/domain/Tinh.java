package com.globits.da.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Tinh {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String tenTinh;

    @OneToMany(mappedBy = "tinh", cascade = CascadeType.ALL)
    List<Huyen> huyenList;
    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTenTinh() {
        return tenTinh;
    }

    public void setTenTinh(String tenTinh) {
        this.tenTinh = tenTinh;
    }
}
