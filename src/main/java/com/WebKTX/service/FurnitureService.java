package com.WebKTX.service;

import com.WebKTX.model.Danhmucnoithat;
import com.WebKTX.model.User;
import org.springframework.stereotype.Service;

@Service
public interface FurnitureService {
    void updateFurniture(Integer id, Danhmucnoithat danhmucnoithat);

    void removeFurniture(Integer id);
}
