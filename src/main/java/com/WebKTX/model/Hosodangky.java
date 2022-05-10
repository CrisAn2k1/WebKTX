package com.WebKTX.model;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "hosodangky")
public class Hosodangky {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_hosodk", nullable = false)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User idUser;

    @Column(name = "mota")
    private String mota;

    @Column(name = "ngaytao")
    private Instant ngaytao;

    @Column(name = "ngaynhanphong")
    private LocalDate ngaynhanphong;

    @Column(name = "ngaytraphong")
    private LocalDate ngaytraphong;

    @Column(name = "trangthai")
    private Boolean trangthai;

    public Boolean getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(Boolean trangthai) {
        this.trangthai = trangthai;
    }

    public LocalDate getNgaytraphong() {
        return ngaytraphong;
    }

    public void setNgaytraphong(LocalDate ngaytraphong) {
        this.ngaytraphong = ngaytraphong;
    }

    public LocalDate getNgaynhanphong() {
        return ngaynhanphong;
    }

    public void setNgaynhanphong(LocalDate ngaynhanphong) {
        this.ngaynhanphong = ngaynhanphong;
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