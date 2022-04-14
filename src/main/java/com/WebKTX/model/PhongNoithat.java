package com.WebKTX.model;

import javax.persistence.*;

@Entity
@Table(name = "phong_noithat", indexes = {
        @Index(name = "id_phongnt_noithat_idx", columnList = "id_noithat")
})
public class PhongNoithat {
    @EmbeddedId
    private PhongNoithatId id;

    @MapsId("idPhong")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_phong", nullable = false)
    private Phong idPhong;

    @MapsId("idNoithat")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_noithat", nullable = false)
    private Danhmucnoithat idNoithat;

    @Column(name = "soluong", nullable = false)
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

    public PhongNoithatId getId() {
        return id;
    }

    public void setId(PhongNoithatId id) {
        this.id = id;
    }
}