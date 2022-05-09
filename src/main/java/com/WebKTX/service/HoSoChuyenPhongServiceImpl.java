package com.WebKTX.service;

import com.WebKTX.model.Hosochuyenphong;
import com.WebKTX.repository.HoSoChuyenPhongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component("hscpService")
public class HoSoChuyenPhongServiceImpl implements HoSoChuyenPhongService {
    @Autowired
    HoSoChuyenPhongRepository hosoChuyenPhongRepo;
    @Override
    public void updateHosochuyenphong(Integer id, Hosochuyenphong hosochuyenphong) {
        Hosochuyenphong hosochuyenphongEdit = hosoChuyenPhongRepo.findById(id).orElse(null);
        hosoChuyenPhongRepo.save(hosochuyenphongEdit);

    }

    @Override
    public void removeHosochuyenphong(Integer id) {
        Hosochuyenphong hosochuyenphongRemove = hosoChuyenPhongRepo.findById(id).orElse(null);
        hosoChuyenPhongRepo.delete(hosochuyenphongRemove);
    }
}
