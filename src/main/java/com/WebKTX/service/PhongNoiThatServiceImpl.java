package com.WebKTX.service;

import com.WebKTX.model.PhongNoithat;
import com.WebKTX.repository.PhongNoiThatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhongNoiThatServiceImpl implements PhongNoiThatService {
    @Autowired
    PhongNoiThatRepository phongNoiThatRepo;
    @Override
    public void updatePhongNoithat(Integer id, PhongNoithat phongNoithat) {
        PhongNoithat furnitureEdit = phongNoiThatRepo.findById(id).orElse(null);
        furnitureEdit.setSoluong(phongNoithat.getSoluong());
        phongNoiThatRepo.save(furnitureEdit);

    }

    @Override
    public void removeFurniture(Integer id) {
//        Danhmucnoithat furnitureRemove = furnitureRepo.findById(id).orElse(null);
//        furnitureRepo.delete(furnitureRemove);
    }
}
