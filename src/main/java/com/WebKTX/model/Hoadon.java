package com.WebKTX.model;

import javax.persistence.*;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "hoadon")
public class Hoadon {
    @Id
    @Column(name = "id_hoadon", nullable = false)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_phong")
    private Phong idPhong;

    @Column(name = "tongtien", nullable = false)
    private Double tongtien;

    @Column(name = "ngayxuatHD", nullable = false)
    private Instant ngayxuatHD;

    @Column(name = "trangthaithanhtoan")
    private Boolean trangthaithanhtoan;

    @OneToMany(mappedBy = "idHoadon")
    private Set<Chitiethoadon> chitiethoadons = new LinkedHashSet<>();

    public Set<Chitiethoadon> getChitiethoadons() {
        return chitiethoadons;
    }

    public void setChitiethoadons(Set<Chitiethoadon> chitiethoadons) {
        this.chitiethoadons = chitiethoadons;
    }

    public Boolean getTrangthaithanhtoan() {
        return trangthaithanhtoan;
    }

    public void setTrangthaithanhtoan(Boolean trangthaithanhtoan) {
        this.trangthaithanhtoan = trangthaithanhtoan;
    }

    public Instant getNgayxuatHD() {
        return ngayxuatHD;
    }

    public void setNgayxuatHD(Instant ngayxuatHD) {
        this.ngayxuatHD = ngayxuatHD;
    }

    public Double getTongtien() {
        return tongtien;
    }

    public void setTongtien(Double tongtien) {
        this.tongtien = tongtien;
    }

    public Phong getIdPhong() {
        return idPhong;
    }

    public void setIdPhong(Phong idPhong) {
        this.idPhong = idPhong;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    //TODO Reverse Engineering! Migrate other columns to the entity
}