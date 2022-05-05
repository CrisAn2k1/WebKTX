package com.WebKTX.model;

import javax.persistence.*;

@Entity
@Table(name = "phong_noithat", indexes = {
        @Index(name = "id_phong_nt_phong_idx", columnList = "id_phong"),
        @Index(name = "id_phong_nt_noithat_idx", columnList = "id_noithat")
})
public class PhongNoithat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_phong", nullable = false)
    private Phong idPhong;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_noithat", nullable = false)
    private Danhmucnoithat idNoithat;

    @Column(name = "soluong")
    private Integer soluong;

    public Integer getSoluong() {
        return soluong;
    }

    public void setSoluong(Integer soluong) {
        this.soluong = soluong;
    }

    public Danhmucnoithat getIdNoithat() {
        return idNoithat;
    }

    public void setIdNoithat(Danhmucnoithat idNoithat) {
        this.idNoithat = idNoithat;
    }

    public Phong getIdPhong() {
        return idPhong;
    }

    public void setIdPhong(Phong idPhong) {
        this.idPhong = idPhong;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}