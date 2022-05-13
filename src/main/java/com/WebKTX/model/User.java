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

    @Transient
    private String confirmPassowrd;

    public String getConfirmPassowrd() {
        return confirmPassowrd;
    }

    public void setConfirmPassowrd(String confirmPassowrd) {
        this.confirmPassowrd = confirmPassowrd;
    }

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

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "truonghoc", length = 150)
    private String truonghoc;

    @Column(name = "nienkhoa")
    private Integer nienkhoa;

    @Column(name = "trangthai")
    private Boolean trangthai;

    @Column(name = "tinh", length = 50)
    private String tinh;

    @Column(name = "huyen", length = 50)
    private String huyen;

    @Column(name = "xa", length = 50)
    private String xa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_phong")
    private Phong idPhong;

    @Column(name = "`cmnd/cccd`", length = 15)
    private String cmndCccd;

//    @Column(name = "`cmnd/cccd_mattruoc`")
//    private String cmndCccdMattruoc;
//
//    @Column(name = "`cmnd/cccd_matsau`")
//    private String cmndCccdMatsau;

    @Column(name = "mssv", length = 15)
    private String mssv;

    @Column(name = "enabled", nullable = false)
    private Boolean enabled = false;

    @Column(name = "verification_code", length = 64)
    private String verificationCode;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "roles_users",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_role"))
    private Set<Role> roles = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idUser")
    private Set<Hosochuyenphong> hosochuyenphongs = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idUser")
    private Set<Hosodangky> hosodangkies = new LinkedHashSet<>();

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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getMssv() {
        return mssv;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }

//    public String getCmndCccdMatsau() {
//        return cmndCccdMatsau;
//    }
//
//    public void setCmndCccdMatsau(String cmndCccdMatsau) {
//        this.cmndCccdMatsau = cmndCccdMatsau;
//    }
//
//    public String getCmndCccdMattruoc() {
//        return cmndCccdMattruoc;
//    }
//
//    public void setCmndCccdMattruoc(String cmndCccdMattruoc) {
//        this.cmndCccdMattruoc = cmndCccdMattruoc;
//    }

    public String getCmndCccd() {
        return cmndCccd;
    }

    public void setCmndCccd(String cmndCccd) {
        this.cmndCccd = cmndCccd;
    }

    public Phong getIdPhong() {
        return idPhong;
    }

    public void setIdPhong(Phong idPhong) {
        this.idPhong = idPhong;
    }

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