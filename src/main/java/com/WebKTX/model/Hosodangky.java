package com.WebKTX.model;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

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

    @Column(name = "ngaytao")
    private Instant ngaytao;

    @Column(name = "ngaynhanphong")
    @DateTimeFormat(pattern="yyyy-MM-dd")

    private Date ngaynhanphong;

    @Column(name = "ngaytraphong")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date ngaytraphong;

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

    public Date getNgaytraphong() {
        return ngaytraphong;
    }

    public void setNgaytraphong(Date ngaytraphong) {
        this.ngaytraphong = ngaytraphong;
    }

    public Date getNgaynhanphong() {
        return ngaynhanphong;
    }

    public void setNgaynhanphong(Date ngaynhanphong) {
        this.ngaynhanphong = ngaynhanphong;
    }

    public Instant getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(Instant ngaytao) {
        this.ngaytao = ngaytao;
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