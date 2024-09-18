package com.globits.da.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Huyen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String tenHuyen;

    @ManyToOne
    @JoinColumn(name= "tinh_id")
    private Tinh tinh;

    @OneToMany(mappedBy = "huyen", cascade = CascadeType.ALL)
    List<Xa> xaList;

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTenHuyen() {
        return tenHuyen;
    }

    public void setTenHuyen(String tenHuyen) {
        this.tenHuyen = tenHuyen;
    }
}
