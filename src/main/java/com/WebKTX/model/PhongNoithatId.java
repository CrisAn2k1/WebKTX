package com.WebKTX.model;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PhongNoithatId implements Serializable {
    private static final long serialVersionUID = -4745140323228267286L;
    @Column(name = "id_phong", nullable = false, length = 5)
    private String idPhong;
    @Column(name = "id_noithat", nullable = false)
    private Integer idNoithat;

    public Integer getIdNoithat() {
        return idNoithat;
    }

    public void setIdNoithat(Integer idNoithat) {
        this.idNoithat = idNoithat;
    }

    public String getIdPhong() {
        return idPhong;
    }

    public void setIdPhong(String idPhong) {
        this.idPhong = idPhong;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idNoithat, idPhong);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PhongNoithatId entity = (PhongNoithatId) o;
        return Objects.equals(this.idNoithat, entity.idNoithat) &&
                Objects.equals(this.idPhong, entity.idPhong);
    }
}