package com.WebKTX.model;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "hosochuyenphong")
public class Hosochuyenphong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_hosocp", nullable = false)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User idUser;

    @Column(name = "mota")
    private String mota;

    @Column(name = "ngaytao")
    private Instant ngaytao;

    @Column(name = "trangthai")
    private Boolean trangthai;

    @Transient
    private Phong phong;

    public Phong getPhong() {
        return phong;
    }

    public void setPhong(Phong phong) {
        this.phong = phong;
    }

    public Boolean getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(Boolean trangthai) {
        this.trangthai = trangthai;
    }

    public Instant getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(Instant ngaytao) {
        this.ngaytao = ngaytao;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    //TODO Reverse Engineering! Migrate other columns to the entity
}