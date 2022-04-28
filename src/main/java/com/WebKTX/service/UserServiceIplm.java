package com.WebKTX.service;

import com.WebKTX.model.User;
import com.WebKTX.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceIplm implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public void updateInfo(Integer id, User user) {
        User userEdit = userRepository.findById(id).orElse(null);
        userEdit.setEmail(user.getEmail());
        userEdit.setHoten(user.getHoten());
        userEdit.setNgaysinh(user.getNgaysinh());
        userEdit.setGioitinh(user.getGioitinh());
        userEdit.setPhone(user.getPhone());
        userEdit.setDiachicutru(user.getDiachicutru());
        userEdit.setTruonghoc(user.getTruonghoc());
        userEdit.setNienkhoa(user.getNienkhoa());
        userEdit.setTinh(user.getTinh());
        userEdit.setHuyen(user.getHuyen());
        userEdit.setXa(user.getXa());
        userRepository.save(userEdit);
    }

    @Override
    public void removeUser(Integer id) {
        User userRemove = userRepository.findById(id).orElse(null);
        userRepository.delete(userRemove);
    }

}
