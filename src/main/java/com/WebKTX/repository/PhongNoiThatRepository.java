package com.WebKTX.repository;

import com.WebKTX.model.Danhmucnoithat;
import com.WebKTX.model.Phong;
import com.WebKTX.model.PhongNoithat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhongNoiThatRepository extends JpaRepository<PhongNoithat, Integer> {
    @Query("SELECT p FROM PhongNoithat p WHERE p.idPhong.id=?1")
    List<PhongNoithat> findByIdPhong(String idPhong);

    PhongNoithat findByIdNoithatAndIdPhong(Danhmucnoithat idNoiThat, Phong idPhong);
}
