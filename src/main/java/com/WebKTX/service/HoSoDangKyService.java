package com.WebKTX.service;

import com.WebKTX.model.Hosodangky;
import org.springframework.stereotype.Service;

@Service
public interface HoSoDangKyService {

    void updateHosodangky(Integer id, Hosodangky hosodangky);

    void removeHosodangky(Integer id);
}
