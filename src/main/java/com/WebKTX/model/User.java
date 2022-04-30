package com.WebKTX.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "user", indexes = {
        @Index(name = "id_user_phong_idx", columnList = "id_phong")
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "username", nullable = false, length = 16)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "hoten", length = 50)
    private String hoten;

    @Column(name = "ngaysinh")
    private LocalDate ngaysinh;

    @Column(name = "gioitinh", length = 10)
    private String gioitinh;

    @Column(name = "phone", length = 11)
    private String phone;

    @Column(name = "diachicutru")
    private String diachicutru;

    @Column(name = "avatar", length = 50)
    private String avatar;

    @Column(name = "truonghoc", length = 150)
    private String truonghoc;

    @Column(name = "nienkhoa")
    private Integer nienkhoa;

    @Column(name = "trangthai")
    private Boolean trangthai;

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    private boolean isEnabled;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_phong")
    private Phong idPhong;

    @OneToMany(mappedBy = "idUser")
    private Set<Hosochuyenphong> hosochuyenphongs = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idUser")
    private Set<Hosodangky> hosodangkies = new LinkedHashSet<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "roles_users",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_role"))
    private Set<Role> roles = new LinkedHashSet<>();

    @Column(name = "tinh", length = 50)
    private String tinh;

    @Column(name = "huyen", length = 50)
    private String huyen;

    @Column(name = "xa", length = 50)
    private String xa;

    public String getXa() {
        return xa;
    }

    public void setXa(String xa) {
        this.xa = xa;
    }

    public String getHuyen() {
        return huyen;
    }

    public void setHuyen(String huyen) {
        this.huyen = huyen;
    }

    public String getTinh() {
        return tinh;
    }

    public void setTinh(String tinh) {
        this.tinh = tinh;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Hosodangky> getHosodangkies() {
        return hosodangkies;
    }

    public void setHosodangkies(Set<Hosodangky> hosodangkies) {
        this.hosodangkies = hosodangkies;
    }

    public Set<Hosochuyenphong> getHosochuyenphongs() {
        return hosochuyenphongs;
    }

    public void setHosochuyenphongs(Set<Hosochuyenphong> hosochuyenphongs) {
        this.hosochuyenphongs = hosochuyenphongs;
    }

    public Phong getIdPhong() {
        return idPhong;
    }

    public void setIdPhong(Phong idPhong) {
        this.idPhong = idPhong;
    }

    public Boolean getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(Boolean trangthai) {
        this.trangthai = trangthai;
    }

    public Integer getNienkhoa() {
        return nienkhoa;
    }

    public void setNienkhoa(Integer nienkhoa) {
        this.nienkhoa = nienkhoa;
    }

    public String getTruonghoc() {
        return truonghoc;
    }

    public void setTruonghoc(String truonghoc) {
        this.truonghoc = truonghoc;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDiachicutru() {
        return diachicutru;
    }

    public void setDiachicutru(String diachicutru) {
        this.diachicutru = diachicutru;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public LocalDate getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(LocalDate ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}