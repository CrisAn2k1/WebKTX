package com.WebKTX.service;

import com.WebKTX.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.text.SimpleDateFormat;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Date;

public class UserDetail implements UserDetails {

    private User user;

    public UserDetail(User user) {
        this.user = user;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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
        return true;
    }

    public String email(){
        return  user.getEmail();
    }

    public String fullName() {
        return user.getHoten();
    }

    public String ngaySinh(){
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String date = this.user.getNgaysinh().format(formatters);
        return date;
    }

    public String gioiTinh(){
        return  user.getGioitinh();
    }

    public String dienThoai() {
        return user.getPhone();
    }

    public String diaChi(){
        return user.getDiachicutru();
    }

    public String truongHoc(){
        return user.getTruonghoc();
    }

    public String nienKhoa(){
        int currentYear = Year.now().getValue();
        String namThu = String.valueOf(currentYear - user.getNienkhoa().intValue()) ;
        return namThu;
    }

}
