package com.globits.da.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class VanBangChungChi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tenVanBang;
    private LocalDate ngayBatDau;
    private LocalDate ngayKetThuc;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenVanBang() {
        return tenVanBang;
    }

    public void setTenVanBang(String tenVanBang) {
        this.tenVanBang = tenVanBang;
    }

    public LocalDate getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(LocalDate ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public LocalDate getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(LocalDate ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }
}