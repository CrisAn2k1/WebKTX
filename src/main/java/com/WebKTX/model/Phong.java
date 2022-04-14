package com.WebKTX.model;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "phong")
public class Phong {
    @Id
    @Column(name = "id_phong", nullable = false, length = 5)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_toanha")
    private Toanha idToanha;

    @Column(name = "loaiphong", length = 30)
    private String loaiphong;

    @Column(name = "soluongSV", nullable = false)
    private Integer soluongSV;

    @OneToMany(mappedBy = "idPhong")
    private Set<PhongNoithat> phongNoithats = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idPhong")
    private Set<User> users = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idPhong")
    private Set<Hoadon> hoadons = new LinkedHashSet<>();

    public Set<Hoadon> getHoadons() {
        return hoadons;
    }

    public void setHoadons(Set<Hoadon> hoadons) {
        this.hoadons = hoadons;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<PhongNoithat> getPhongNoithats() {
        return phongNoithats;
    }

    public void setPhongNoithats(Set<PhongNoithat> phongNoithats) {
        this.phongNoithats = phongNoithats;
    }

    public Integer getSoluongSV() {
        return soluongSV;
    }

    public void setSoluongSV(Integer soluongSV) {
        this.soluongSV = soluongSV;
    }

    public String getLoaiphong() {
        return loaiphong;
    }

    public void setLoaiphong(String loaiphong) {
        this.loaiphong = loaiphong;
    }

    public Toanha getIdToanha() {
        return idToanha;
    }

    public void setIdToanha(Toanha idToanha) {
        this.idToanha = idToanha;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    //TODO Reverse Engineering! Migrate other columns to the entity
}