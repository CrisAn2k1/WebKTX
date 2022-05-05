package com.WebKTX.repository;

import com.WebKTX.model.Danhmucnoithat;
import com.WebKTX.model.PhongNoithat;
import com.WebKTX.model.PhongNoithatId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FurnitureRepository extends JpaRepository<PhongNoithat, PhongNoithatId> {
    @Query("SELECT u FROM PhongNoithat u WHERE u.id = ?1")
    PhongNoithat findById(PhongNoithat idPhong);

    @Query("SELECT x FROM Danhmucnoithat x WHERE x.id = ?1")
    Danhmucnoithat findById(Danhmucnoithat danhmucnoithat);
}
