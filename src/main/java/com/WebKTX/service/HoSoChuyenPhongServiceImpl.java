package com.WebKTX.service;

import com.WebKTX.model.Hosochuyenphong;
import com.WebKTX.model.User;
import com.WebKTX.repository.HoSoChuyenPhongRepository;
import com.WebKTX.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;

@Service
@Component("hscpService")
public class HoSoChuyenPhongServiceImpl implements HoSoChuyenPhongService {
    @Autowired
    HoSoChuyenPhongRepository hosoChuyenPhongRepo;

    @Autowired
    UserRepository userRepo;

    @Override
    public void updateHosochuyenphong(Integer id, Hosochuyenphong hosochuyenphong) {
        Hosochuyenphong hosochuyenphongEdit = hosoChuyenPhongRepo.findById(id).orElse(null);
        hosochuyenphongEdit.setTrangthai(hosochuyenphong.getTrangthai());
        hosoChuyenPhongRepo.save(hosochuyenphongEdit);
    }

    @Override
    public void removeHosochuyenphong(Integer id) {

        Hosochuyenphong hosochuyenphongRemove = hosoChuyenPhongRepo.findById(id).orElse(null);
        hosoChuyenPhongRepo.delete(hosochuyenphongRemove);
    }

    @Override
    public void addChuyenPhong(Integer idUser,Hosochuyenphong hosochuyenphong){
        User getUser = userRepo.findById(idUser).orElse(null);
        Instant instant = Instant.now();
        hosochuyenphong.setNgaytao(instant);
        hosochuyenphong.setIdUser(getUser);
        hosochuyenphong.setTrangthai(false);
        hosoChuyenPhongRepo.save(hosochuyenphong);
    }
}
