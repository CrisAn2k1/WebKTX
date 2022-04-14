package com.WebKTX.model;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "dichvudiennuoc")
public class Dichvudiennuoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dichvu", nullable = false)
    private Integer id;

    @Column(name = "tendichvu", length = 50)
    private String tendichvu;

    @Column(name = "dongia")
    private Double dongia;

    @OneToMany(mappedBy = "idDichvu")
    private Set<Chitiethoadon> chitiethoadons = new LinkedHashSet<>();

    public Set<Chitiethoadon> getChitiethoadons() {
        return chitiethoadons;
    }

    public void setChitiethoadons(Set<Chitiethoadon> chitiethoadons) {
        this.chitiethoadons = chitiethoadons;
    }

    public Double getDongia() {
        return dongia;
    }

    public void setDongia(Double dongia) {
        this.dongia = dongia;
    }

    public String getTendichvu() {
        return tendichvu;
    }

    public void setTendichvu(String tendichvu) {
        this.tendichvu = tendichvu;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}