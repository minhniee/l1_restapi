package com.globits.da.domain;

import javax.persistence.*;

@Entity
public class Xa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String tenXa;

    @ManyToOne
    @JoinColumn(name = "huyen_id")
    private Huyen huyen;
    // Getters and Setters


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTenXa() {
        return tenXa;
    }

    public void setTenXa(String tenXa) {
        this.tenXa = tenXa;
    }
}