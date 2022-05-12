package com.WebKTX.service;

import com.WebKTX.model.Hosochuyenphong;
import org.springframework.stereotype.Service;

@Service
public interface HoSoChuyenPhongService {

    void updateHosochuyenphong(Integer id, Hosochuyenphong hosochuyenphong);

    void removeHosochuyenphong(Integer id);

    void processChuyenPhong(Integer id, Hosochuyenphong hosochuyenphong);
}
