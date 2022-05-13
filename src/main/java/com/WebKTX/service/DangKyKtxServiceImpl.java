package com.WebKTX.service;

import com.WebKTX.model.User;
import com.WebKTX.repository.RoleRepository;
import com.WebKTX.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DangKyKtxServiceImpl implements DangKyKtxService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public void registerKTX(Integer id, User user) throws IOException {
        User formEdit = userRepository.findById(id).orElse(null);
        formEdit.setCmndCccd(user.getCmndCccd());
        formEdit.setHoten(user.getHoten());
        formEdit.setGioitinh(user.getGioitinh());
        formEdit.setNgaysinh(user.getNgaysinh());
        formEdit.setPhone(user.getPhone());
        formEdit.setMssv(user.getMssv());
        formEdit.setTruonghoc(user.getTruonghoc());
        formEdit.setNienkhoa(user.getNienkhoa());
        formEdit.setTinh(user.getTinh());
        formEdit.setHuyen(user.getHuyen());
        formEdit.setXa(user.getXa());
        formEdit.setAvatar(user.getAvatar());
        userRepository.save(formEdit);
    }
}
