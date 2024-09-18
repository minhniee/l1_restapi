package com.globits.da.domain;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnore;

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
    @JsonIgnore
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

    public Tinh getTinh() {
        return tinh;
    }

    public void setTinh(Tinh tinh) {
        this.tinh = tinh;
    }

    public List<Xa> getXaList() {
        return xaList;
    }

    public void setXaList(List<Xa> xaList) {
        this.xaList = xaList;
    }

}
