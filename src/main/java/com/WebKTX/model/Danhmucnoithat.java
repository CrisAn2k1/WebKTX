package com.WebKTX.model;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "danhmucnoithat")
public class Danhmucnoithat {
    @Id
    @Column(name = "id_noithat", nullable = false)
    private Integer id;

    @Column(name = "ten", nullable = false, length = 50)
    private String ten;

    @Column(name = "soluongton", nullable = false)
    private Integer soluongton;

    @OneToMany(mappedBy = "idNoithat")
    private Set<PhongNoithat> phongNoithats = new LinkedHashSet<>();

    public Set<PhongNoithat> getPhongNoithats() {
        return phongNoithats;
    }

    public void setPhongNoithats(Set<PhongNoithat> phongNoithats) {
        this.phongNoithats = phongNoithats;
    }

    public Integer getSoluongton() {
        return soluongton;
    }

    public void setSoluongton(Integer soluongton) {
        this.soluongton = soluongton;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    //TODO Reverse Engineering! Migrate other columns to the entity
}