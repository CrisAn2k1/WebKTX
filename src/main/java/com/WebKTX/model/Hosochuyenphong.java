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