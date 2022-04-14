package com.WebKTX.model;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RolesUserId implements Serializable {
    private static final long serialVersionUID = 8837883264573748399L;
    @Column(name = "id_user", nullable = false)
    private Integer idUser;
    @Column(name = "id_role", nullable = false)
    private Integer idRole;

    public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, idRole);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RolesUserId entity = (RolesUserId) o;
        return Objects.equals(this.idUser, entity.idUser) &&
                Objects.equals(this.idRole, entity.idRole);
    }
}