package com.WebKTX.service;

import com.WebKTX.model.Role;
import com.WebKTX.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class UserDetail implements UserDetails {

    private User user;

    public UserDetail(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> roles = user.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
            System.out.println("Quyen:" + role.getName());
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.getEnabled();
    }

    public Integer id(){
        return user.getId();
    }

    public String email(){
        if(user.getEmail()==null)
        {
            return "Đang cập nhật";
        }
        return  user.getEmail();
    }

    public String fullName() {
        return user.getHoten();
    }

    public String ngaySinh(){
        if(user.getNgaysinh()==null)
        {
            return "Đang cập nhật";
        }
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String date = this.user.getNgaysinh().format(formatters);
        return date;
    }

    public String gioiTinh(){
        if (user.getGioitinh()==null){
            return "Đang cập nhật";
        }
        return user.getGioitinh();
    }

    public String dienThoai() {
        if (user.getPhone()==null){
            return "Đang cập nhật";
        }
        return user.getPhone();
    }

    public String diaChi(){
        if (user.getDiachicutru()==null){
            return "Đang cập nhật";
        }
        return user.getDiachicutru();
    }

    public String tinh(){
        if (user.getTinh()==null){
            return "Đang cập nhật";
        }
        return user.getTinh();
    }

    public String huyen(){
        if (user.getHuyen()==null){
            return "Đang cập nhật";
        }
        return user.getHuyen();
    }

    public String xa(){
        if (user.getXa()==null){
            return "Đang cập nhật";
        }
        return user.getXa();
    }

    public String mssv(){
        if (user.getMssv()==null){
            return "Đang cập nhật";
        }
        return user.getMssv();
    }
    public String cmndCccd(){
        if (user.getCmndCccd()==null){
            return "Đang cập nhật";
        }
        return user.getCmndCccd();
    }
    public String idphong(){
        if (user.getIdPhong()==null || user.getIdPhong().getId()==null){
            return "";
        }
        return " - " + user.getIdPhong().getId();
    }

    public String truongHoc(){
        if (user.getTruonghoc()==null){
            return "Đang cập nhật";
        }
        return user.getTruonghoc();
    }

    public String nienKhoa(){
        if (user.getNienkhoa()==null){
            return "Đang cập nhật";
        }
        int currentYear = Year.now().getValue();
        String namThu = String.valueOf(currentYear - user.getNienkhoa().intValue()) ;
        return namThu;
    }
}
