package com.WebKTX.model;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "toanha")
public class Toanha {
    @Id
    @Column(name = "id_toanha", nullable = false, length = 5)
    private String id;

    @Column(name = "ten", length = 20)
    private String ten;

    @OneToMany(mappedBy = "idToanha")
    private Set<Phong> phongs = new LinkedHashSet<>();

    public Set<Phong> getPhongs() {
        return phongs;
    }

    public void setPhongs(Set<Phong> phongs) {
        this.phongs = phongs;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}