package com.WebKTX.model;

import javax.persistence.*;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "hoadon", indexes = {
        @Index(name = "id_hoadon_phong_idx", columnList = "id_phong")
})
public class Hoadon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_hoadon", nullable = false)
    private Integer id;

    @Column(name = "tongtien")
    private Double tongtien;

    @Column(name = "ngayxuatHD", nullable = false)
    private Instant ngayxuatHD;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_phong")
    private Phong idPhong;

    @Column(name = "trangthaithanhtoan")
    private Boolean trangthaithanhtoan;

    public Integer getChisodien() {
        return chisodien;
    }

    public void setChisodien(Integer chisodien) {
        this.chisodien = chisodien;
    }

    public Integer getChisonuoc() {
        return chisonuoc;
    }

    public void setChisonuoc(Integer chisonuoc) {
        this.chisonuoc = chisonuoc;
    }

    @Transient
    private Integer chisodien;

    @Transient
    private Integer chisonuoc;

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

    public Phong getIdPhong() {
        return idPhong;
    }

    public void setIdPhong(Phong idPhong) {
        this.idPhong = idPhong;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}