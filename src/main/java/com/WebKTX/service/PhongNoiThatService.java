package com.WebKTX.service;

import com.WebKTX.model.PhongNoithat;
import org.springframework.stereotype.Service;

@Service
public interface PhongNoiThatService {

    void updatePhongNoithat(Integer id, PhongNoithat phongNoithat);

    void removeFurniture(Integer id);
}
