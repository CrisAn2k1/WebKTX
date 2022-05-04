package com.WebKTX.repository;

import com.WebKTX.model.Danhmucnoithat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FurnitureRepository extends JpaRepository<Danhmucnoithat, Integer> {
    @Query("SELECT u FROM Danhmucnoithat u WHERE u.id = ?1")
    Danhmucnoithat findById(String id);

}
