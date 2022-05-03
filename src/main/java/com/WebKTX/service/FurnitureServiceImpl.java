package com.WebKTX.service;

import com.WebKTX.model.Danhmucnoithat;
import com.WebKTX.model.User;
import com.WebKTX.repository.FurnitureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FurnitureServiceImpl implements FurnitureService{
    @Autowired
    FurnitureRepository furnitureRepo;
    @Override
    public void updateFurniture(Integer id, Danhmucnoithat danhmucnoithat) {
        Danhmucnoithat furnitureEdit = furnitureRepo.findById(id).orElse(null);
        furnitureEdit.setId(danhmucnoithat.getId());
        furnitureEdit.setTen(danhmucnoithat.getTen());
        furnitureEdit.setSoluongton(danhmucnoithat.getSoluongton());
        furnitureRepo.save(furnitureEdit);

    }

    @Override
    public void removeFurniture(Integer id) {
        Danhmucnoithat furnitureRemove = furnitureRepo.findById(id).orElse(null);
        furnitureRepo.delete(furnitureRemove);
    }
}
