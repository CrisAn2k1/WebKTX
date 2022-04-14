package com.WebKTX.model;

import javax.persistence.*;

@Entity
@Table(name = "chitiethoadon")
public class Chitiethoadon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_CTHD", nullable = false)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_hoadon")
    private Hoadon idHoadon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_dichvu")
    private Dichvudiennuoc idDichvu;

    @Column(name = "chisotieuthu")
    private Integer chisotieuthu;

    @Column(name = "dongia")
    private Double dongia;

    @Column(name = "thanhtien")
    private Double thanhtien;

    public Double getThanhtien() {
        return thanhtien;
    }

    public void setThanhtien(Double thanhtien) {
        this.thanhtien = thanhtien;
    }

    public Double getDongia() {
        return dongia;
    }

    public void setDongia(Double dongia) {
        this.dongia = dongia;
    }

    public Integer getChisotieuthu() {
        return chisotieuthu;
    }

    public void setChisotieuthu(Integer chisotieuthu) {
        this.chisotieuthu = chisotieuthu;
    }

    public Dichvudiennuoc getIdDichvu() {
        return idDichvu;
    }

    public void setIdDichvu(Dichvudiennuoc idDichvu) {
        this.idDichvu = idDichvu;
    }

    public Hoadon getIdHoadon() {
        return idHoadon;
    }

    public void setIdHoadon(Hoadon idHoadon) {
        this.idHoadon = idHoadon;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    //TODO Reverse Engineering! Migrate other columns to the entity
}