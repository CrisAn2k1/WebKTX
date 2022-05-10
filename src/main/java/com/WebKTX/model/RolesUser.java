package com.WebKTX.model;

import javax.persistence.*;

@Entity
@Table(name = "roles_users", indexes = {
        @Index(name = "id_roles_user_idx", columnList = "id_user"),
        @Index(name = "id_role_roles_idx", columnList = "id_role")
})
public class RolesUser {
    @EmbeddedId
    private RolesUserId id;

    @MapsId("idUser")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_user", nullable = false)
    private User idUser;

    @MapsId("idRole")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_role", nullable = false)
    private Role idRole;

    public Role getIdRole() {
        return idRole;
    }

    public void setIdRole(Role idRole) {
        this.idRole = idRole;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    public RolesUserId getId() {
        return id;
    }

    public void setId(RolesUserId id) {
        this.id = id;
    }
}